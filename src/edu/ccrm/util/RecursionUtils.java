package edu.ccrm.util;

import java.nio.file.*;
import java.io.IOException;

public class RecursionUtils {
    public static void listFilesByDepth(Path dir, int depth) throws IOException {
        if (depth < 0) return;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                System.out.println(" ".repeat(depth * 2) + entry.getFileName());
                if (Files.isDirectory(entry)) {
                    listFilesByDepth(entry, depth + 1);
                }
            }
        }
    }
}
