package hello;

import javax.persistence.Embeddable;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 01/01/2020
 * Time: 11:03 오후
 **/
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipCode;

    public Address() {
    }

    public Address (String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
