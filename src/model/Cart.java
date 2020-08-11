package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, Integer> cartList;

    public Cart() {
        this.cartList = new HashMap<>();
    }

    public Map<String, Integer> getCartList() {
        return cartList;
    }

    public void setCartList(Map<String, Integer> cartList) {
        this.cartList = cartList;
    }

    public void addItem(String menuID, int quantity) {
        boolean isExist = cartList.keySet().contains(menuID);
        if (isExist) {
            cartList.put(menuID, cartList.get(menuID) + quantity);
        } else {
            cartList.put(menuID, quantity);
        }
    }

    public void removeItem(String menuID) {
        boolean isExist = cartList.keySet().contains(menuID);
        if (isExist) {
            cartList.remove(menuID);
        }
    }

    public void increaseQuantity(String menuID) {
        boolean isExist = cartList.keySet().contains(menuID);
        if (isExist) {
            if (cartList.get(menuID) > 1) {
                cartList.put(menuID, cartList.get(menuID) - 1);
            }
        }
    }

    public void decreaseQuantity(String menuID) {
        boolean isExist = cartList.keySet().contains(menuID);

        if (isExist) {
            if (cartList.get(menuID) < 10) {
                cartList.put(menuID, cartList.get(menuID) + 1);
            }
        }
    }
}
