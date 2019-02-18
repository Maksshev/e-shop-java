package dto;

import java.util.List;
import java.util.Map;

public class Cart implements Identifiable {
    private int userId;
    private List<Map<Commodity, Integer>> cartItems;

    public Cart(int userId, List<Map<Commodity, Integer>> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public int getUserId() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public List<Map<Commodity, Integer>> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Map<Commodity, Integer>> cartItems) {
        this.cartItems = cartItems;
    }

    public int getId() {
        return 0;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", cartItems=" + cartItems +
                '}';
    }
}
