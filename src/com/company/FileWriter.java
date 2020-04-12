package com.company;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    private String fileContent;
    FileWriter(String fileContent){
         this.fileContent=fileContent;
    }
    void writeToFile() throws IOException {
        FileOutputStream out = new FileOutputStream("./output.asm");
        out.write(fileContent.getBytes());
        out.close();
    }
}
