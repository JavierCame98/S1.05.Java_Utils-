package ListAlphabeticallyTheDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class DirectoryAlphabetically {

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

    public void listAlphabeticallyTheDirectory(Path listDirectory) throws IOException{
        if(!Files.isDirectory(listDirectory)){
            throw new IllegalArgumentException("The File is not a directory");
        }

        try(Stream<Path> stream = Files.list(listDirectory)){
            stream.sorted(Comparator.comparing(path -> path.getFileName().toString(), String.CASE_INSENSITIVE_ORDER))
                    .forEach(path -> System.out.println(path.getFileName()));
        }
    }



}
