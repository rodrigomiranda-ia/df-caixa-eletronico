package br.com.danielfarias.caixaeletronico.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import br.com.danielfarias.caixaeletronico.exception.CaixaEletronicoException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	 @ExceptionHandler(CaixaEletronicoException.class)
	   protected ResponseEntity<Object> validarSemEstoqueNotas(CaixaEletronicoException ex) {
	       ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }
	 
	 private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	     return new ResponseEntity<>(apiError, apiError.getStatus());
	 }

}
