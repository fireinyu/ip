package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvBackedList<T extends CsvSerializable> extends ArrayList<T>{
    final Path file;

    public CsvBackedList(Path file) throws IOException {
        super();
        this.file = file;
        this.fetch();
    }

    public void close() throws IOException {
            this.stream()
                    .map(CsvSerializable::extract)
                    .map(row -> String.join(",", row))
                    .reduce((accum, res) -> String.join("\n",accum))
                    .ifPresent(str -> {
                        try {
                            Files.writeString(this.file, str);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
    }

    public abstract T parse(List<String> item) throws CorruptedTaskFileException;

    private void fetch() throws IOException {
        Files.createDirectories(this.file.getParent());
        if (!this.file.toFile().exists()) {
            Files.createFile(this.file);
        }
        Files.readAllLines(this.file).stream()
                .map(line -> line.split(","))
                .map(List::of)
                .map(this::parse)
                .forEach(this::add);
    }
}
