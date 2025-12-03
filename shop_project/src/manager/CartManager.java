package manager;

import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Order;
import promotion.PromotionManager;

import java.util.ArrayList;
import java.util.List;

/*
This class manages cart
- add products to cart
- remove products from cart
- get products from cart
- place an order (creates an Order object and processes it with OrderProcessor)
 */
public class CartManager {
    private final Cart cart;
    private final ProductManager productManager;
    private final OrderProcessor orderProcessor;

    public CartManager(Cart cart, ProductManager productManager, OrderProcessor orderProcessor) {
        this.cart = cart;
        this.productManager = productManager;
        this.orderProcessor = orderProcessor;
    }

    public void addToCart(int id) throws ProductNotFoundException, ProductNotAvailableException {
        CartItem item = new CartItem(productManager.getProductFromMagazineById(id));
        cart.add(item);
    }

    public boolean removeFromCart(int id) {
        if (cart.remove(id)) {
            return productManager.addProductBackToMagazine(id);
        } else {
            return false;
        }
    }

    public void placeAnOrder(Customer customer) {
        List<CartItem> itemsCopy = new ArrayList<>(cart.getItems());
        Order order = new Order(customer, itemsCopy);
        orderProcessor.takeAnOrder(order);
        cart.emptyCart();
    }

    public void placeAnOrder(Customer customer, PromotionManager promotionManager, String userCode) {
        List<CartItem> itemsCopy = new ArrayList<>(cart.getItems());
        Order order = new Order(customer, itemsCopy, promotionManager, userCode);
        orderProcessor.takeAnOrder(order);
        cart.emptyCart();
    }

    public List<CartItem> getProductsFromCart() {
        return cart.getItems();
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    // looking for a product with the given id (by id that a product has in magazine)
    public CartItem getItemById(int id) {
        return cart.getItems().stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Nie znaleziono podanego produktu w koszyku"));
    }
}
