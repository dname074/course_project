package model;

import util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Invoice {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String cart;
    private final LocalDateTime orderDate;
    private final BigDecimal totalPrice;

    public Invoice(String firstName, String lastName, String address,
                   String cart, LocalDateTime orderDate, BigDecimal totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cart = cart;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String toCsv() {
        return String.format("%s;%s;%s;%s;%s;%s\n",
                firstName, lastName, address, cart,
                orderDate.format(Constants.DATE_FORMAT), totalPrice.toPlainString());
    }

    @Override
    public String toString() {
        return String.format(
                "Faktura:\nImie %s, Nazwisko %s, Adres %s\nZamówione produkty \n%s\nData zamówienia %s\nKoszt zamówienia %.2f",
                firstName, lastName, address, cart,
                orderDate.format(Constants.DATE_FORMAT), totalPrice);
    }
}
