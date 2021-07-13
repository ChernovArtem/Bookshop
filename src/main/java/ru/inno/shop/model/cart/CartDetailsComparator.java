package ru.inno.shop.model.cart;

import java.util.Comparator;

/**
 * Сравнение двух CartDetails
 */
public class CartDetailsComparator implements Comparator<CartDetails> {

    @Override
    public int compare(CartDetails c1, CartDetails c2) {
        return c1.getCartDetailBook().compareTo(c2.getCartDetailBook());
    }
}
