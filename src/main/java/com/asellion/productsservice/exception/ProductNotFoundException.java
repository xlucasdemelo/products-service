package com.asellion.productsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.asellion.productsservice.util.ProductConstants;


/**
 * Exception to be throw when a product object does not exist in the database
 * 
 * @author lucas
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ProductConstants.THE_GIVEN_ID_DOES_NOT_MATCH_ANY_DATA_IN_THE_SYSTEM)
public class ProductNotFoundException extends ProductException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9031009494518118430L;

}
