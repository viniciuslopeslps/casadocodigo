package br.com.casadocodigo.shop.controllers;


import br.com.casadocodigo.shop.models.PaymentData;
import br.com.casadocodigo.shop.models.ShopCart;
import br.com.casadocodigo.shop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    //Callable deixa o metodo assincrono
    public Callable<ModelAndView> resolvePayment(@AuthenticationPrincipal User user, RedirectAttributes model) {
        return () -> {
            try {
                shopCart.getTotal();
                String url = "http://book-payment.herokuapp.com/payment";
                restTemplate.postForObject(url, new PaymentData(shopCart.getTotal()), String.class);
                //adiciona na mensage para ficar disponivel no produto listar
                // model atribute é na página de redirecionamento e flash é usado como mensagem de sucesso
                model.addFlashAttribute("message", "Pagamento realizado com sucesso");
                sentEmailProduct(user);
            } catch (Exception e) {
                System.out.println(e);
                model.addFlashAttribute("message", "Valor maior que o permitido!");
            }
            return new ModelAndView("redirect:/produtos/lista");
        };
    }

    private void sentEmailProduct(User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Compra finalizada com sucesso");
        email.setTo("viniciuslopeslps@gmail.com");
        email.setText("Compra aprovada com sucesso no valor de " + shopCart.getTotal());
        email.setFrom("compras@casadocodigo.com.br");
        mailSender.send(email);
    }


}
