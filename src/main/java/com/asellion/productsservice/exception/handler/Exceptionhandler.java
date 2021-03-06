package com.asellion.productsservice.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.asellion.productsservice.util.ProductConstants;

/**
 * Class That will handle Exceptions from the application
 * @author lucas
 *
 */
@RestControllerAdvice
public class Exceptionhandler {

	
		
		/**
		 * Handles the exception HttpMessageNotReadableException that will usually happens when the client send a null payload 
		 * 
		 * @param e
		 * @return
		 */
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(value = HttpMessageNotReadableException.class)
		public ResponseEntity<String> exception(final HttpMessageNotReadableException e) {
			return new ResponseEntity<String>(ProductConstants.BODY_SHOULD_NOT_BE_NULL, HttpStatus.BAD_REQUEST);
		}
		
		/**
		 * Handles the exception ConstraintViolationException that will happen when try to persist a mandatory field
		 * 
		 * @param e
		 * @return
		 */
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(value = ConstraintViolationException.class)
		public ResponseEntity<String> exception(final ConstraintViolationException e) {
			return new ResponseEntity<String>(ProductConstants.REQUIRED_FIELDS_ARE_MISSING, HttpStatus.BAD_REQUEST);
		}
		
		/**
		 * 
		 * @param e
		 * @return
		 */
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(value = TransactionSystemException.class)
		public ResponseEntity<String> exception(final TransactionSystemException e) {
			return new ResponseEntity<String>(ProductConstants.REQUIRED_FIELDS_ARE_MISSING, HttpStatus.BAD_REQUEST);
		}
}
