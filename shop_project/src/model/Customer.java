package model;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;

    public Customer(String firstName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%d\n%s\n",
                firstName, lastName, age, address);
    }
}
