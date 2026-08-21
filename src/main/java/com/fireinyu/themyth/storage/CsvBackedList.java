package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;
import com.fireinyu.themyth.exceptions.FileAccessException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvBackedList<T extends CsvSerializable> extends ArrayList<T> {
    private LinesDisk storage;
    private boolean linked;

    public CsvBackedList() {
        super();
        this.linked = false;
    }

    public void open(Path path) {
        this.storage = new LinesDisk(path);
        this.linked = true;
        this.fetch();
    }

    public void close() {
        if (!this.linked) {
            return;
        }
        this.storage.writeLines(
                this.stream()
                    .map(CsvSerializable::extract)
                    .map(row -> String.join(",", row))
                );
        this.linked = false;
    }

    public Path getPath() {
        return this.storage.getPath();
    }

    public abstract T parse(List<String> item) throws CorruptedTaskFileException;

    private void fetch() {
        if (!this.linked) {
            return;
        }
        this.clear();
        this.linked = false;
        super.addAll(this.storage.readLines()
                .map(line -> line.split(","))
                .map(List::of)
                .map(this::parse)
                .toList()
        );
        this.linked = true;
    }
}
