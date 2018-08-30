package com.waes.scalableweb.handler;

import com.waes.scalableweb.dto.error.ErrorDetail;
import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ttdduman
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle ResourceNotFoundException
     * @param rnfe
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {

        logger.debug("ResourceNotFoundException is handled");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle MissingBinaryDataException
     * @param mbde
     * @param request
     * @return
     */
    @ExceptionHandler(MissingBinaryDataException.class)
    public ResponseEntity<?> handleMissingBinaryDataException(MissingBinaryDataException mbde, HttpServletRequest request) {
        logger.debug("MissingBinaryDataException is handled");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Binary Data Missing");
        errorDetail.setDetail(mbde.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }




    /**
     * Handle IllegalArgumentException
     * @param ile
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ile, HttpServletRequest request) {
        logger.debug("IllegalArgumentException is handled");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Binary Data Missing");
        errorDetail.setDetail(ile.getMessage());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle MethodArgumentNotValidException
     * @param manve
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.debug("MethodArgumentNotValidException is handled");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail(manve.getMessage());

        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

    /**
     * Handle HttpMessageNotReadableException
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        logger.debug("HttpMessageNotReadableException is handled");
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

}
