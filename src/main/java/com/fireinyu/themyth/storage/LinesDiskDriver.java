package com.fireinyu.themyth.storage;

import java.util.stream.Stream;

/**
 * Driver interface for reading and writing to a storage on Disk.<br><br>
 * File is read and written line-wise.
 */
public interface LinesDiskDriver {

    /**
     * Read the lines in the disk storage associated with this LinesDiskDriver into a Stream buffer<br><br>
     * @return Stream of lines in the disk storage
     * @see Stream
     * @see String
     */
    Stream<String> readLines();

    /**
     * Write all lines from a String Stream into the disk storage associated with this LinesDiskDriver<br><br>
     * @param lines Stream of lines to be written
     * @see Stream
     * @see String
     */
    void writeLines(Stream<String> lines);

    /**
     * Get the descriptor associated with this LinesDiskDriver<br><br>
     * @return the descriptor associated with this LinesDiskDriver
     * @see String
     */
    String getDescriptor();
}
