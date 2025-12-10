package org.example._4_Read_Txt_And_Print;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class DirectoryCreator {

    public boolean createDirectory(String directoryName) throws IOException {
        Objects.requireNonNull(directoryName, "directoryName cannot be null");
        Path path = Paths.get(directoryName);
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                System.out.println("Directory already exists: " + path.toAbsolutePath());
                return true;
            } else {
                throw new IOException("A non-directory file exists at: " + path);
            }
        }
        Files.createDirectories(path);
        System.out.println("Directory created: " + path.toAbsolutePath());
        return true;
    }
}
