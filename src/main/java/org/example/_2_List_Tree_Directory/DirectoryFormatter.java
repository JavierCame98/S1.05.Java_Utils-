package org.example._2_List_Tree_Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DirectoryFormatter {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.systemDefault());

    public static String formatDirectory(Path dir, int depth) throws IOException {
        String lastModified = FORMATTER.format(Instant.ofEpochMilli(Files.getLastModifiedTime(dir).toMillis()));
        return indentation(depth) + "[D] " + dir.getFileName() + " last modified=" + lastModified + "\n";
    }

    public static String formatFile(Path file, int depth) throws IOException {
        String lastModified = FORMATTER.format(Instant.ofEpochMilli(Files.getLastModifiedTime(file).toMillis()));
        return indentation(depth) + "[F] " + file.getFileName() + " last modified=" + lastModified + "\n";
    }

    private static String indentation(int depth) {
        return " ".repeat(depth * 2);
    }
}
