package org.example._2_List_Tree_Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class ListTreeDirectory {

    public boolean createDirectory(String directoryName) throws IOException {
        Objects.requireNonNull(directoryName, "DirectoryName cannot be null");
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

    public void listAlphabeticallyTheDirectory(Path listDirectory) throws IOException {
        if (!Files.isDirectory(listDirectory)) {
            throw new IllegalArgumentException("The File is not a directory");
        }

        try (Stream<Path> stream = Files.list(listDirectory)) {
            stream.sorted(Comparator.comparing(path -> path.getFileName().toString(), String.CASE_INSENSITIVE_ORDER))
                    .forEach(path -> System.out.println(path.getFileName()));
        }
    }


    public void listTreeAlphabetically(Path directory, Appendable out) throws IOException {
        walk(directory, 0, out);
    }

    private void walk(Path directory, int depth, Appendable out) throws IOException {

        out.append(DirectoryFormatter.formatDirectory(directory, depth));

        try (Stream<Path> stream = Files.list(directory)) {
            for (Path child : stream.sorted(Comparator.comparing(p -> p.getFileName().toString(),
                    String.CASE_INSENSITIVE_ORDER)).toList()) {
                if (Files.isDirectory(child)) {
                    walk(child, depth + 1, out);
                } else {
                    out.append(DirectoryFormatter.formatFile(child, depth + 1));
                }
            }


        }
    }

}
