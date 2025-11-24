package model;

public class Client {
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;

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
