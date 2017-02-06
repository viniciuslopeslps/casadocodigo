package br.com.casadocodigo.shop.controllers;

import br.com.casadocodigo.shop.daos.ProductDAO;
import br.com.casadocodigo.shop.models.CartItem;
import br.com.casadocodigo.shop.models.PriceType;
import br.com.casadocodigo.shop.models.Product;
import br.com.casadocodigo.shop.models.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/carrinho")
@Controller
//cria escopo de request
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CartController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ShopCart shopCart;


    @RequestMapping("/add")
    public ModelAndView add(Integer productId, PriceType priceType) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
        CartItem cartItem = createItem(productId, priceType);
        shopCart.add(cartItem);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView items() {
        return new ModelAndView("/shopCart/items");
    }

    public CartItem createItem(Integer productId, PriceType priceType) {
        Product product = productDAO.find(productId);
        return new CartItem(product, priceType);
    }

    @RequestMapping("/excluir")
    public ModelAndView remove(Integer productId, PriceType priceType) {
        shopCart.remove(productId, priceType);
        return new ModelAndView("redirect:/carrinho");
    }
}
