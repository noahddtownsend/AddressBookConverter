package com.noahtownsend.addressbookconverter;

import java.lang.reflect.Field;

public class Contact {
    private String CustomerID;
    private String CompanyName;
    private String ContactName;
    private String ContactTitle;
    private String Address;
    private String City;
    private String Email;
    private String PostalCode;
    private String Country;
    private String Phone;
    private String Fax;

    public StringBuilder toJsonString() throws IllegalAccessException {
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        for (Field field : Contact.class.getDeclaredFields()) {
            json.append("\t\t\"").append(field.getName()).append("\":\"").append(field.get(this)).append("\",\n");
        }
        json.append("\n\t}");

        return json;
    }

    public StringBuilder toXmlString() throws IllegalAccessException {
        StringBuilder xml = new StringBuilder();

        xml.append("<Contact>\n");
        for (Field field : Contact.class.getDeclaredFields()) {
            xml.append("\t\t<").append(field.getName()).append(">").append(field.get(this)).append("</").append(field.getName()).append(">\n");
        }
        xml.append("\t</Contact>");

        return xml;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactTitle() {
        return ContactTitle;
    }

    public void setContactTitle(String contactTitle) {
        ContactTitle = contactTitle;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }
}
