package org.physicscode.controller.advice;

import org.physicscode.dto.pojo.output.error.ErrorDTO;
import org.physicscode.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class DefaultControllerAdvice {

    public Mono<ResponseEntity<ErrorDTO>> handleServiceException(ServiceException e) {

        return Mono.just(ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorDTO(e.getMessage())));
    }
}
