package br.com.casadocodigo.shop.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.shop.models.Product;

public class ProductValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "title", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "description", "field.required");
		Product produto = (Product) target;
		if (produto.getPages() <= 0) {
			errors.rejectValue("pages", "field.required");
		}

	}
}
