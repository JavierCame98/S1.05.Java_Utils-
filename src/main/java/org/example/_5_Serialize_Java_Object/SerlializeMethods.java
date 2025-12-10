package org.example._5_Serialize_Java_Object;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SerlializeMethods {

    public void serializeObject(Object obj, Path filePath) throws IOException {
        Objects.requireNonNull(obj, "Object can't be null");
        Objects.requireNonNull(filePath, "Path can't be null");

        if (!(obj instanceof Serializable)) {
            throw new IllegalArgumentException("The object has to be an instance of Serializable.");
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(obj);

        }
    }

    public Object deserializeObject(Path filePath) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(filePath, "Path can't be null");

        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            throw new java.nio.file.NoSuchFileException("File not found");
        }

        try (FileInputStream fileIn = new FileInputStream(filePath.toFile());
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            Object obj = in.readObject();
            return obj;

        }
    }

}
