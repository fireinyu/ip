package com.fireinyu.themyth.storage;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;
import com.fireinyu.themyth.exceptions.FileAccessException;

/**
 * ArrayList that can be synced with a csv file on disk.<br><br>
 * File access is managed through a LinesDisk.
 * @param <T> The type of elements in this list, which must be CsvSerializable.
 * @see LinesDisk
 */
public abstract class CsvBackedList<T extends CsvSerializable> extends ArrayList<T> {

    private LinesDisk storage;
    private boolean linked;

    /**
     * Initialises a CsvBackedList.<br><br>
     * It is initially not backed by any LinesDisk so it acts as an ArrayList.<br>
     * Call open() to sync to a LinesDisk.
     * @see LinesDisk
     */
    public CsvBackedList() {
        super();
        this.linked = false;
    }

    /**
     * Creates a LinesDisk instance to back this CsvBackedList<br><br>
     * Automatically pulls the LineDisk content into this CsvBackedList if opened successfully<br>
     * File content is deserialized into T instances<br>
     * Warning: Previous data in this CsvBackedList will be replaced. <br>
     * @param path path to the CSV file for the LinesDisk instance
     * @throws FileAccessException if the file cannot be created or opened for reading
     * @throws CorruptedTaskFileException if the file content is corrupted and cannot be deserialized
     * @see Path
     * @see LinesDisk
     * @see CsvSerializable
     */
    public void open(Path path) {
        this.storage = new LinesDisk(path);
        this.linked = true;
        this.fetch();
    }

    /**
     * Creates a LinesDisk instance to back this CsvBackedList<br><br>
     * Automatically pulls the LineDisk content into this CsvBackedList if opened successfully<br>
     * File content is deserialized into T instances<br>
     * Warning: Previous data in this CsvBackedList will be replaced. <br>
     * @param resourceName name of CSV resource file for the LinesDisk instance
     * @throws FileAccessException if the file cannot be created or opened for reading
     * @throws CorruptedTaskFileException if the file content is corrupted and cannot be deserialized
     * @see Path
     * @see LinesDisk
     * @see CsvSerializable
     */
    public void open(String resourceName) {
        try {
            this.open(Paths.get(CsvBackedList.class.getClassLoader().getResource(resourceName).toURI()));
        } catch (URISyntaxException e) {
            throw new FileAccessException(resourceName);
        }
    }

    /**
     * Close the LinesDisk instance that backs this CsvBackedList, if any<br><br>
     * Automatically writes the content of this CsvBackedList into the LinesDisk before closing
     * @throws FileAccessException if the file cannot be created or opened for writing
     * @see Path
     * @see LinesDisk
     */
    public void close() {
        if (!this.linked) {
            return;
        }
        this.storage.writeLines(
                this.stream()
                    .map(CsvSerializable::extract)
                    .map(List::stream)
                    .map(stream -> stream.map(field -> field.replace("\"", "\"\"")))
                    .map(stream -> stream.map(field -> field.replace("\n", "\"\n\"")))
                    .map(stream -> stream.map(field -> field.replace(",", "\",\"")))
                    .map(Stream::toList)
                    .map(row -> String.join(", ", row))
        );
        this.linked = false;
    }

    /**
     * Get the file path associated with the LinesDisk instance that backs this CsvBackedList
     * @return the file path associated with the LinesDisk instance that backs this CsvBackedList
     * @see Path
     * @see LinesDisk
     */
    public Path getPath() {
        if (this.storage == null) {
            return null;
        }
        return this.storage.getPath();
    }

    /**
     * Parses a string array from a line in the CSV file into an object of type T.
     *
     * @param item The string array representing a row in the CSV.
     * @return The parsed object.
     * @throws CorruptedTaskFileException if the data is corrupted.
     */
    protected abstract T parse(String... item) throws CorruptedTaskFileException;

    private T parse(List<String> item) throws CorruptedTaskFileException {
        return this.parse(item.toArray(new String[0]));
    }
    /**
     * Fetches data from the backing file.
     * It clears the current list and repopulates it with data from the file.
     * The data is deserialized from CSV format to objects of type T.
     */
    private void fetch() {
        if (!this.linked) {
            return;
        }
        // Temporarily unlink the list to prevent any overridden add/clear methods
        // from causing recursive file writes while we are repopulating the list
        // from the file.
        this.linked = false;
        this.clear();
        super.addAll(this.storage.readLines()
                .map(line -> line.split(", "))
                .map(Arrays::stream)
                .map(stream -> stream.map(field -> field.replace("\",\"", ",")))
                .map(stream -> stream.map(field -> field.replace("\"\n\"", "\n")))
                .map(stream -> stream.map(field -> field.replace("\"\"", "\"")))
                .map(Stream::toList)
                .map(this::parse)
                .toList()
        );
        this.linked = true;
    }
}
