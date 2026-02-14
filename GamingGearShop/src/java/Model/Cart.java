package Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<String, ProductDTO> cart;

    public Cart() {
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void add(ProductDTO product) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        // Nếu đã có món này thì cộng dồn số lượng
        if (this.cart.containsKey(product.getProductID())) {
            int currentQuantity = this.cart.get(product.getProductID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        // Cập nhật vào giỏ hàng
        this.cart.put(product.getProductID(), product);
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, int quantity) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
        }
    }
}
