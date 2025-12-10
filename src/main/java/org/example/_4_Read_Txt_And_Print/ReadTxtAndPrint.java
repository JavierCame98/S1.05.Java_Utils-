package org.example._4_Read_Txt_And_Print;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ReadTxtAndPrint {

    public void readTxt(Path inputTxt) throws IOException {
        Objects.requireNonNull(inputTxt, "InputTxt can't be null");
        if(Files.exists(inputTxt)){
            throw new FileNotFoundException("The file doesn't exist");
        }
        if(!Files.isRegularFile(inputTxt)){
            throw new IllegalArgumentException("It's not a regular File");
        }

        try(BufferedReader reader = Files.newBufferedReader(inputTxt, StandardCharsets.UTF_8)){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }
}
