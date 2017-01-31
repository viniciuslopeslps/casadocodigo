package br.com.casadocodigo.shop.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ShopCart {

    private Map<CartItem, Integer> items = new LinkedHashMap<CartItem, Integer>();

    public void add(CartItem item) {
        items.put(item, getQtd(item) + 1);
    }

    private int getQtd(CartItem item) {
        if (!items.containsKey(item)) {
            items.put(item, 0);
        }
        return items.get(item);
    }

    public int getQtd() {
        return items.values().stream().reduce(0, (next, acumulator) -> next + acumulator);
    }
}
