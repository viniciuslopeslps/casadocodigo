package br.com.casadocodigo.shop.controllers;


import br.com.casadocodigo.shop.models.PaymentData;
import br.com.casadocodigo.shop.models.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@RequestMapping("/pagamento")
@Controller
public class PaymentController {

    @Autowired
    private ShopCart shopCart;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    //Callable deixa o metodo assincrono
    public Callable<ModelAndView> resolvePayment(RedirectAttributes model) {
        return () -> {
            try {
                shopCart.getTotal();
                String url = "http://book-payment.herokuapp.com/payment";
                restTemplate.postForObject(url, new PaymentData(shopCart.getTotal()), String.class);
                //adiciona na mensage para ficar disponivel no produto listar
                // model atribute é na página de redirecionamento e flash é usado como mensagem de sucesso
                model.addFlashAttribute("message", "Pagamento realizado com sucesso");
            } catch (Exception e) {
                System.out.println(e);
                model.addFlashAttribute("message", "Valor maior que o permitido!");
            }
            return new ModelAndView("redirect:/produtos/lista");
        };
    }

}
