package br.com.casadocodigo.shop.controllers;

import br.com.casadocodigo.shop.daos.ProductDAO;
import br.com.casadocodigo.shop.infra.FileSaver;
import br.com.casadocodigo.shop.models.PriceType;
import br.com.casadocodigo.shop.models.Product;
import br.com.casadocodigo.shop.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductDAO productDao;

    @Autowired
    private FileSaver fileSaver;

    @InitBinder
    public void iniBinder(WebDataBinder binder) {
        binder.addValidators(new ProductValidation());
    }

    @RequestMapping("/form")
    public ModelAndView form(Product product) {
        ModelAndView modelAndView = new ModelAndView("products/form");
        modelAndView.addObject("types", PriceType.values());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    @CacheEvict(value = "homeProducts", allEntries = true)
    public ModelAndView save(MultipartFile summaryFile, @Valid Product product, BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return form(product);
        }

        String path = fileSaver.write("files", summaryFile);
        product.setSummaryPath(path);

        productDao.save(product);
        redirectAttributes.addFlashAttribute("message", "Produto cadastrado com sucesso!");
        return new ModelAndView("redirect:produtos/lista");
    }

    @RequestMapping("/lista")
    public ModelAndView list() {
        List<Product> products = productDao.list();
        ModelAndView modelAndView = new ModelAndView("/products/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("/detalhe/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("/products/detail");
        Product product = productDao.find(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
}
