package org.example._3_Save_In_TxtFile;

import org.example._2_List_Tree_Directory.DirectoryFormatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class SaveInTxtFile {
    public void saveTreeToTxt(Path directory, Path outputTxt) throws IOException {

        Objects.requireNonNull(directory, "Directory can't be null");
        Objects.requireNonNull(outputTxt, "outputTxt can't be null");

        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("The path of" + directory + "is not valid.");
        }

        Path parentDir = outputTxt.getParent();
        if (parentDir != null) {
            Files.createDirectories(parentDir);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(outputTxt, StandardCharsets.UTF_8)) {
            walk(directory, 0, writer);

        }
    }

    private void walk(Path directory, int depth, Appendable out) throws IOException {

        out.append(org.example._2_List_Tree_Directory.DirectoryFormatter.formatDirectory(directory, depth));

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
