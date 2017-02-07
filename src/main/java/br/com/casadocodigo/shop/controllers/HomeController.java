package br.com.casadocodigo.shop.controllers;

import br.com.casadocodigo.shop.daos.ProductDAO;
import br.com.casadocodigo.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/")
    @Cacheable(value = "homeProducts")
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Product> products = productDAO.list();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
