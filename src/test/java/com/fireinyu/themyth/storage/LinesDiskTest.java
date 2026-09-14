package com.fireinyu.themyth.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.fireinyu.themyth.exceptions.FileAccessException;

/**
 * Unit tests for {@link LinesDisk}.
 */
public class LinesDiskTest {

    @TempDir
    Path tempDir;

    /**
     * Tests that getPath returns the path assigned during construction.
     */
    @Test
    public void getPath_returnsConfiguredPath() {
        Path targetPath = tempDir.resolve("sample.txt");
        LinesDisk disk = new LinesDisk(targetPath);
        assertEquals(targetPath, disk.getPath());
    }

    /**
     * Tests that reading from a non-existent file automatically creates the file and returns an empty stream.
     */
    @Test
    public void readLines_nonExistentFile_createsFileAndReturnsEmpty() {
        Path targetPath = tempDir.resolve("subfolder").resolve("newfile.txt");
        assertFalse(Files.exists(targetPath));

        LinesDisk disk = new LinesDisk(targetPath);
        List<String> lines = disk.readLines().toList();

        assertTrue(Files.exists(targetPath));
        assertTrue(lines.isEmpty());
    }

    /**
     * Tests writing lines to a file and reading them back.
     */
    @Test
    public void writeAndReadLines_persistsContentCorrectly() {
        Path targetPath = tempDir.resolve("data.txt");
        LinesDisk disk = new LinesDisk(targetPath);

        disk.writeLines(Stream.of("first line", "second line", "third line"));
        List<String> readResult = disk.readLines().toList();

        assertEquals(List.of("first line", "second line", "third line"), readResult);
    }

    /**
     * Tests that an invalid path throws {@link FileAccessException} on read.
     */
    @Test
    public void readLines_onDirectoryPath_throwsFileAccessException() {
        // tempDir is a directory, not a file, so reading it as a file will cause an IOException
        LinesDisk disk = new LinesDisk(tempDir);
        assertThrows(FileAccessException.class, () -> disk.readLines().toList());
    }

    /**
     * Tests that an invalid path throws {@link FileAccessException} on write.
     */
    @Test
    public void writeLines_onDirectoryPath_throwsFileAccessException() {
        // tempDir is a directory, not a file, so writing lines to it will cause an IOException
        LinesDisk disk = new LinesDisk(tempDir);
        assertThrows(FileAccessException.class, () -> disk.writeLines(Stream.of("fail")));
    }
}
