package model;

import util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final List<Product> cart;
    private final LocalDateTime orderDate;
    private final BigDecimal totalPrice;

    public Invoice(String firstName, String lastName, String address,
                   List<Product> cart, LocalDateTime orderDate, BigDecimal totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cart = cart;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public String toCsv() {
        return String.format("%s;%s;%s;%s;%s;%.2f",
                firstName, lastName, address, cart,
                orderDate.format(Constants.dateFormat), totalPrice);
    }

    @Override
    public String toString() {
        return String.format(
                "Faktura:\nImie %s, Nazwisko %s, Adres %s\nZamówione produkty \n%s\nData zamówienia %s\nKoszt zamówienia %.2f",
                firstName, lastName, address, cart,
                orderDate.format(Constants.dateFormat), totalPrice);
    }
}
