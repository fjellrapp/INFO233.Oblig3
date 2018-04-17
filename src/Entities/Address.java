package Entities;

public class Address {

    /**
     * Entitetsbønner som representerer tabellen til Invoice
     */
    private int addressId;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String postalTown;

    public Address(int addressId, String streetNumber, String streetName, String postalCode, String postalTown) {
        this.addressId = addressId;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.postalTown = postalTown;
    }

    public Address() {

    }

    /**
     * Getters for Address
     *
     * @return
     */

    public int getAddressId() {
        return addressId;
    }

    /**
     * Mutatorer for address-bønnen
     *
     * @param addressId
     */

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalTown() {
        return postalTown;
    }

    public void setPostalTown(String postalTown) {
        this.postalTown = postalTown;
    }
}
