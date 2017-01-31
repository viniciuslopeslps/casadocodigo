package br.com.casadocodigo.shop.controllers;

import br.com.casadocodigo.shop.daos.ProductDAO;
import br.com.casadocodigo.shop.models.CartItem;
import br.com.casadocodigo.shop.models.PriceType;
import br.com.casadocodigo.shop.models.Product;
import br.com.casadocodigo.shop.models.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/carrinho")
@Controller
public class CartController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ShopCart shopCart;


    @RequestMapping("/add")
    public ModelAndView add(Integer productId, PriceType priceType) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produtos/lista");
        CartItem cartItem = createItem(productId, priceType);
        shopCart.add(cartItem);
        return modelAndView;
    }

    public CartItem createItem(Integer productId, PriceType priceType) {
        Product product = productDAO.find(productId);
        return new CartItem(product, priceType);
    }
}
