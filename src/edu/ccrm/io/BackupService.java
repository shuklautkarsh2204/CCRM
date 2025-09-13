package edu.ccrm.io;

import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    public Path backupData(Path sourceDir, Path backupRoot) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = backupRoot.resolve("backup_" + timestamp);
        Files.createDirectories(backupDir);
        Files.walk(sourceDir).forEach(src -> {
            try {
                Path dest = backupDir.resolve(sourceDir.relativize(src));
                if (Files.isDirectory(src)) {
                    Files.createDirectories(dest);
                } else {
                    Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return backupDir;
    }

    public long computeBackupSize(Path backupDir) throws IOException {
        return Files.walk(backupDir)
            .filter(Files::isRegularFile)
            .mapToLong(p -> {
                try { return Files.size(p); } catch (IOException e) { return 0; }
            }).sum();
    }
}
