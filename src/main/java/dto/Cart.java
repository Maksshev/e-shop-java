package dto;

import java.util.List;

public class Cart implements Identifiable {
    private User user;
    private List<Commodity> cartItems;

    public Cart(User user, List<Commodity> cartItems) {
        this.user = user;
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Commodity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Commodity> cartItems) {
        this.cartItems = cartItems;
    }

    public int getId() {
        return 0;
    }
}
