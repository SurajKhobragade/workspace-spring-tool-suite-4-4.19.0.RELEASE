package org.sr.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.sr.bean.ErrorResponse;

@ControllerAdvice
public class AuthenticationControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus().value(),
				LocalDateTime.now());
		return ResponseEntity.status(exception.getStatus()).body(errorResponse);
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ErrorResponse> handleApiException(ApiException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus().value(),
				LocalDateTime.now());
		return ResponseEntity.status(exception.getStatus()).body(errorResponse);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus().value(),
				LocalDateTime.now());
		return ResponseEntity.status(exception.getStatus()).body(errorResponse);
	}
}
