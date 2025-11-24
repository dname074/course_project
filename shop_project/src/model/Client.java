package model;

public class Client {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String city;
    private final String country;

    public Client(String firstName, String lastName, int age, String country, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("%s \n%s \n%d \n%s \n%s\n",
                firstName, lastName, age, city, country);
    }
}
