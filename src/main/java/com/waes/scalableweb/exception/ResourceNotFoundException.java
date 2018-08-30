package com.waes.scalableweb.exception;

import com.waes.scalableweb.enums.Side;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource not found exception.
 * Mostly used for not exist id in repository
 *
 * @author ttdduman
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ResourceNotFoundException build(Long id) {
        return new ResourceNotFoundException("Binary data with id " + id + " not found");

    }
}