package org.physicscode.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EMPTY_SUPERMARKET_DATA          ("No data could be retrieve for the requested supermarket",             HttpStatus.NO_CONTENT),
    FAILED_TO_INSERT_FEEDBACK       ("No feedback could be introduced for the requested supermarket",       HttpStatus.INTERNAL_SERVER_ERROR);


    private final String msg;
    private final HttpStatus httpStatus;


}
