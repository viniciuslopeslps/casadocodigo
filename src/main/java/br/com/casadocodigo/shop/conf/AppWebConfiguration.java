	package br.com.casadocodigo.shop.conf;

	import br.com.casadocodigo.shop.controllers.HomeController;
	import br.com.casadocodigo.shop.daos.ProductDAO;
	import br.com.casadocodigo.shop.infra.FileSaver;
	import br.com.casadocodigo.shop.models.ShopCart;
	import org.springframework.context.MessageSource;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.support.ReloadableResourceBundleMessageSource;
	import org.springframework.format.datetime.DateFormatter;
	import org.springframework.format.datetime.DateFormatterRegistrar;
	import org.springframework.format.support.DefaultFormattingConversionService;
	import org.springframework.format.support.FormattingConversionService;
	import org.springframework.web.multipart.MultipartResolver;
	import org.springframework.web.multipart.support.StandardServletMultipartResolver;
	import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
	import org.springframework.web.servlet.view.InternalResourceViewResolver;
	
	@EnableWebMvc
	//pra cada package precisa colocar um novo desse aqui  (usado nas beans)
	@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class, FileSaver.class, ShopCart.class })
	public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
		//confiugração para pasta de views
		@Bean
		public InternalResourceViewResolver internalResourceViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/views/");
			resolver.setSuffix(".jsp");
			//setExposeContextBeansAsAttributes
			resolver.setExposedContextBeanNames("shopCart");
			return resolver;
		}
	
		//configuracao para messages.properties
		@Bean
		public MessageSource messageSource(){
		    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		    messageSource.setBasename("/WEB-INF/messages");
		    messageSource.setDefaultEncoding("UTF-8");
		    messageSource.setCacheSeconds(1);
		    return messageSource;
		}
		
		//configuracao para modelo de data
		@Bean
		public FormattingConversionService mvcConversionService(){
			DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
			DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
			dateFormatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
			dateFormatterRegistrar.registerFormatters(conversionService);
			return conversionService;
		}
	
		//configuracao para arquivo
		@Bean
		public MultipartResolver multipartResolver(){
			return new StandardServletMultipartResolver();
		}

		//
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		}
	}