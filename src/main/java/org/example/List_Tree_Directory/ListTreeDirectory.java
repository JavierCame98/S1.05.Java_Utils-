package org.example.List_Tree_Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ListTreeDirectory {

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


    public void listTreeAlphabetically (Path directory) throws IOException {
        walk(directory, 0, System.out);
    }

    private void walk (Path directory, int depth, Appendable out) throws IOException{

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM.yyyy HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        String lastModified = dateFormatter.format(Instant.ofEpochMilli((Files.getLastModifiedTime(directory).toMillis())));

        String dirName = directory.getFileName() == null ?
                directory.toAbsolutePath().toString() : directory.getFileName().toString();

        out.append(indentation(depth))
                .append("[D] ")
                .append(dirName)
                .append(" last modified = " )
                .append(lastModified);


        try(Stream<Path> stream = Files.list(directory)){

            List<Path> children = stream
                    .sorted(Comparator.comparing(p-> p.getFileName().toString(),
                            String.CASE_INSENSITIVE_ORDER))
                    .toList();

            for(Path c : children){
                if(Files.isDirectory(c)){
                    walk(c, depth +1, out);
                } else{
                    String fileLastModified = dateFormatter
                            .format((Instant.ofEpochMilli(Files.getLastModifiedTime(c).toMillis())));

                    out.append(indentation(depth +1))
                            .append("[F] ")
                            .append(c.getFileName().toString())
                            .append(" last modified =")
                            .append(fileLastModified);
                }
            }
        }
    }

    private String indentation (int depth){
        char[] spaces = new char[depth +2];
        Arrays.fill(spaces, ' ');
        return new String(spaces);
    }

}
