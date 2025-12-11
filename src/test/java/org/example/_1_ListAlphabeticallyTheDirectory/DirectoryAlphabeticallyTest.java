package org.example._1_ListAlphabeticallyTheDirectory;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTests {

    @Test
    void testCreateDirectory_createsDirectoryAndThrowsIfFileExists(@TempDir Path tempDir) throws IOException {
            DirectoryAlphabetically lister = new DirectoryAlphabetically();

            String newDirName = tempDir.resolve("miNuevoDir").toString();
            boolean created = lister.createDirectory(newDirName);
            Path createdPath = Path.of(newDirName);
            assertTrue(created, "createDirectory should return true when directory is created or already exists");
            assertTrue(Files.exists(createdPath) && Files.isDirectory(createdPath),
                    "The directory should exist after createDirectory call");

            Path filePath = tempDir.resolve("existingFile");
            Files.createFile(filePath);
            String filePathString = filePath.toString();
            IOException ex = assertThrows(IOException.class, () -> lister.createDirectory(filePathString),
                    "When a non-directory file exists at the path, createDirectory should throw IOException");
            assertTrue(ex.getMessage().contains("non-directory") || ex.getMessage().contains(filePathString));
        }




    @Test
    void testListAlphabeticallyTheDirectory_sortsEntriesAndValidatesInput(@TempDir Path tempDir) throws IOException {
        DirectoryAlphabetically lister = new DirectoryAlphabetically();

        Path f1 = Files.createFile(tempDir.resolve("b-file.txt"));
        Path f2 = Files.createFile(tempDir.resolve("A-file.txt"));
        Path f3 = Files.createFile(tempDir.resolve("c-file.txt"));
        Path subdir = Files.createDirectory(tempDir.resolve("subDir"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        lister.listAlphabeticallyTheDirectory(tempDir);
        System.out.flush();

        String output = baos.toString().trim();
        String[] lines = output.split("\\R+");

        assertEquals(4, lines.length, "Debe imprimirse una línea por entrada del directorio");
        List<String> printedNames = List.of(lines[0].trim(), lines[1].trim(), lines[2].trim(), lines[3].trim());
        List<String> expectedOrder = List.of("A-file.txt", "b-file.txt", "c-file.txt", "subDir");
        assertEquals(expectedOrder, printedNames, "Los nombres deben aparecer ordenados alfabeticamente (case-insensitive)");

        Path someFile = Files.createFile(tempDir.resolve("solo-file"));
        assertThrows(IllegalArgumentException.class, () -> lister.listAlphabeticallyTheDirectory(someFile),
                "Si el path no es un directorio, debe lanzarse IllegalArgumentException");
    }
}