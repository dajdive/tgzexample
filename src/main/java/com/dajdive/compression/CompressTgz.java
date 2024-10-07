package com.dajdive.compression;

import lombok.experimental.UtilityClass;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@UtilityClass
public class CompressTgz {

    public static void compressFiles(List<Path> paths, Path output)
            throws IOException {
        try (OutputStream fOut = Files.newOutputStream(output);
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {
            for (Path path : paths) {
                if (!Files.isRegularFile(path)) {
                    throw new IOException("Support only for regular files!");
                }
                TarArchiveEntry tarEntry = new TarArchiveEntry(path.toFile(), path.getFileName().toString());
                tOut.putArchiveEntry(tarEntry);
                // copy file to TarArchiveOutputStream
                Files.copy(path, tOut);
                tOut.closeArchiveEntry();
            }
            tOut.finish();
        }
    }
}
