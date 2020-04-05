package org.physicscode.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EMPTY_SUPERMARKET_DATA ("No data could be retrieve for the requested supermarket", HttpStatus.NO_CONTENT);

    private final String msg;
    private final HttpStatus httpStatus;


}
