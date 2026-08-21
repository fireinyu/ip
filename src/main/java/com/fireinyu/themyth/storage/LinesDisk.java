package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.exceptions.FileAccessException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LinesDisk {

    private Path path;

    public Path getPath() {
        return path;
    }

    public LinesDisk(Path path) {
        this.path = path;
    }

    public Stream<String> readLines() {
        try {
            if (!this.path.toFile().exists()) {
                Files.createDirectories(this.path.getParent());
                Files.createFile(this.path);
            }
            return Files.readAllLines(this.path).stream();
        } catch (IOException e) {
            throw new FileAccessException(this.path.toString());
        }
    }

    public void writeLines(Stream<String> lines) {
        try {
            if (!this.path.toFile().exists()) {
                Files.createDirectories(this.path.getParent());
                Files.createFile(this.path);
            }
            Files.write(this.path, lines.toList());
        } catch (IOException e) {
            throw new FileAccessException(this.path.toString());
        }
    }
}
