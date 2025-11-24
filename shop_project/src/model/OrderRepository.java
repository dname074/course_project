package model;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public List<Order> getAllOrders() {
        return orderList;
    }
}