package com.noahtownsend.addressbookconverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void toJsonString(File outputFile) throws IllegalAccessException, IOException {
        FileWriter fw = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("[\n");
        for (int i = 0; i < contacts.size(); ++i) {
            bw.write("\t" + contacts.get(i).toJsonString());
            if (i != contacts.size() - 1) {
                bw.write(",\n");
            }
        }

        bw.write("\n]");
        bw.close();
        fw.close();
    }

    public void toXmlString(File outputFile) throws IllegalAccessException, IOException {
        FileWriter fw = new FileWriter(outputFile, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("<AddressBook>\n");

        for (Contact contact : contacts) {
            bw.write("\t" + contact.toXmlString() + "\n");
        }
        bw.write("</AddressBook>");
        bw.close();
        fw.close();
    }

    public void fromXmlString(String xml) throws IllegalAccessException {
        String[] contactStrings = xml.substring(xml.indexOf("<AddressBook>") + 13, xml.indexOf("</AddressBook>") - 1).split("</(C|c)ontact>");
        for (String contactString : contactStrings) {
            Contact contact = new Contact();
            for (Field field : Contact.class.getDeclaredFields()) {
                int index = contactString.indexOf(field.getName());
                if (index >= 0) {
                    String fieldValue = contactString.split(field.getName())[1];
                    field.setAccessible(true);
                    try {
                        field.set(contact, fieldValue.substring(fieldValue.indexOf('>') + 1, fieldValue.lastIndexOf('<')));
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                    }
                }
            }
            contacts.add(contact);
        }
    }

    public void fromJsonString(String json) throws IllegalAccessException {
        String[] contactStrings = json.substring(json.indexOf("[") + 1, json.indexOf("]") - 1).split("}");
        for (String contactString : contactStrings) {
            Contact contact = new Contact();
            for (Field field : Contact.class.getDeclaredFields()) {
                int index = contactString.indexOf(field.getName());
                if (index >= 0) {
                    String fieldValue = contactString.split(field.getName())[1].split(",")[0];
                    field.setAccessible(true);
                    try {
                        field.set(contact, fieldValue.substring(fieldValue.indexOf(":\"") + 2, fieldValue.lastIndexOf('\"')));
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                    }
                }
            }
            contacts.add(contact);
        }
    }
}
