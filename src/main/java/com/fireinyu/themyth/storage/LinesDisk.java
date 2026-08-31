package com.fireinyu.themyth.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.fireinyu.themyth.exceptions.FileAccessException;

/**
 * Driver class for reading and writing to a file on Disk.<br><br>
 * File is read and written line-wise.
 * @see LinesDisk
 */
public class LinesDisk {

    private Path path;

    /**
     * Initialises a LinesDisk for line-wise access to a file at a given path.<br><br>
     * @param path path to the file
     * @see Path
     * @see Files
     */
    public LinesDisk(Path path) {
        this.path = path;
    }

    /**
     * Get the file path associated with this LinesDisk<br><br>
     * @return the file path associated with this LinesDisk
     * @see Path
     */
    public Path getPath() {
        return path;
    }

    /**
     * Read the lines in the file associated with this LinesDisk into a Stream buffer<br><br>
     * @return Stream of lines in the file
     * @see Stream
     * @see String
     */
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

    /**
     * Write all lines from a String Stream into the file associated with this LinesDisk<br><br>
     * @param lines Stream of lines to be written
     * @see Stream
     * @see String
     */
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
