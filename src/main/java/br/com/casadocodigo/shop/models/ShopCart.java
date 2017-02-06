package br.com.casadocodigo.shop.models;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
//cria escopo de sessão
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopCart implements Serializable {

    private Map<CartItem, Integer> items = new LinkedHashMap<CartItem, Integer>();

    public void add(CartItem item) {
        items.put(item, getQtd(item) + 1);
    }

    public Integer getQtd(CartItem item) {
        if (!items.containsKey(item)) {
            items.put(item, 0);
        }
        return items.get(item);
    }

    public int getQtd() {
        return items.values().stream().reduce(0, (next, acumulator) -> next + acumulator);
    }

    public BigDecimal getTotal(CartItem item) {
        return item.getTotal(getQtd(item));
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items.keySet()) {
            total = total.add(getTotal(item));
        }
        return total;
    }

    public Collection<CartItem> getItems() {
        return items.keySet();
    }

    public void setItems(Map<CartItem, Integer> items) {
        this.items = items;
    }

    public void remove(Integer productId, PriceType priceType) {
        Product product = new Product();
        product.setId(productId);
        items.remove(new CartItem(product, priceType));
    }
}
