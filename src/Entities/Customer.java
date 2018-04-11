package Entities;

public class Customer {

    private int customerId;
    private String customerName;
    private int address;
    private String phoneNumber;
    private String billingAccount;

    public Customer(int id, String name,int address, String phoneNumber, String billingAccount){
        this.customerId = id;
        customerName = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.billingAccount = billingAccount;
    }

    public Customer(){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }
}
