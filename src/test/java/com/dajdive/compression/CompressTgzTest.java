package com.dajdive.compression;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompressTgzTest {

    private static final Long OUTPUT_FILE_SIZE = 168L;
    @Test
    public void testCompressMultipleFiles() {
        try {
            Path path1 = Paths.get("/Users/duanejohnson/repos/tgzexample/src/test/resources/test_file1.txt");
            Path path2 = Paths.get("/Users/duanejohnson/repos/tgzexample/src/test/resources/test_file2.txt");
            Path output = Paths.get("/Users/duanejohnson/repos/tgzexample/src/test/resources/output1.tgz");

            List<Path> paths = Arrays.asList(path1, path2);

            CompressTgz.compressFiles(paths, output);

            assertEquals("output1.tgz", output.getFileName().toString());
            // tgz file size should be 168 bytes
            assertEquals(OUTPUT_FILE_SIZE, output.toFile().length());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
