package br.com.casadocodigo.shop.controllers;


import br.com.casadocodigo.shop.models.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/pagamento")
@Controller
public class PaymentController {

    @Autowired
    private ShopCart shopCart;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public ModelAndView resolvePayment(RedirectAttributes model) {
        shopCart.getTotal();
        //adiciona na mensage para ficar disponivel no produto listar
        // model atribute é na página de redirecionamento e flash é usado como mensagem de sucesso
        model.addFlashAttribute("message", "Pagamento realizado com sucesso");
        return new ModelAndView("redirect:/produtos/lista");
    }

}
