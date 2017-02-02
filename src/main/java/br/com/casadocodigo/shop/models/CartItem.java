package br.com.casadocodigo.shop.models;

import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private PriceType priceType;

    public CartItem(Product product, PriceType priceType) {
        this.priceType = priceType;
        this.product = product;
    }

    public BigDecimal getPrice() {
        return product.priceTo(priceType);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        if (product != null ? !product.equals(cartItem.product) : cartItem.product != null) return false;
        return priceType == cartItem.priceType;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (priceType != null ? priceType.hashCode() : 0);
        return result;
    }

    public BigDecimal getTotal(int qtd) {
        return this.getPrice().multiply(new BigDecimal(qtd));
    }
}
