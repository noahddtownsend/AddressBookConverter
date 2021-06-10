package com.noahtownsend.addressbookconverter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        if (args.length != 2) {
            System.out.println("Usage! Expected 1 argument (path/to/input/address/book/file, path/to/ouput/address/book/file)");
        }

        String filePath = args[0];
        File file = new File(args[1]);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            System.out.println("File already exists!");
            return;
        }

        AddressBook addressBook = new AddressBook();
        if (filePath.substring(filePath.lastIndexOf('.')).equals(".xml")) {
            addressBook.fromXmlString(readFile(filePath));
            addressBook.toJsonString(file);
        } else {
            addressBook.fromJsonString(readFile(filePath));
            addressBook.toXmlString(file);
        }
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
