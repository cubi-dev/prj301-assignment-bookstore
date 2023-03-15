/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungnv.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class cartObject implements Serializable {

    private Map<String, Integer> items;

    /**
     * @return the items
     */
    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String sku) {
//1. Check item's id is existed
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
//2. when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
//3. when items has existed, checking existed id
        int quantity = 1;
        if (this.items.containsKey(sku)) {
            quantity = this.items.get(sku) + 1;
        }
//4. update items
        this.items.put(sku, quantity);
    }

    
 
    public void removeItemFromCart(String sku) {
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
//        1. check existed items (kiem tra gio co hay chua)
        if (this.items == null) {
            return;
        }
//        2. check existed item name 
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
}
