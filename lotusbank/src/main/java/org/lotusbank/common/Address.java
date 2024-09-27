package org.lotusbank.common;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String state;
    private String city;
    private String zipCode;

    public Address(String street, String state, String city, String zipCode) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", street, city, state, zipCode);
    }
}
