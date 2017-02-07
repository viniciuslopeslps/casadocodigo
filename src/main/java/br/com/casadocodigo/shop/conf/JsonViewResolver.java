package br.com.casadocodigo.shop.conf;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

// classe de configuração para quando a pessoa acessar .json retornar um json
//sem a necessidade de criar um método igual com um respose body

public class JsonViewResolver implements org.springframework.web.servlet.ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setPrettyPrint(true);
        return jsonView;
    }
}
