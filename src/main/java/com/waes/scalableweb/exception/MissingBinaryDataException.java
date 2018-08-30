package com.waes.scalableweb.exception;


import com.waes.scalableweb.enums.Side;

/**
 * Missing Binary Data Exception.
 * if one or two sides of the binary data is missing, exception occurs
 * @author ttdduman
 */
public class MissingBinaryDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MissingBinaryDataException() {
    }

    public MissingBinaryDataException(String message) {
        super(message);
    }

    public MissingBinaryDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public static MissingBinaryDataException build(Long id, Side side) {

        if(side.equals(Side.LEFT)){
            return new MissingBinaryDataException("Left side is missing. Call first /diff/"+id+"/left");
        }else{
            return new MissingBinaryDataException("Right side is missing. Call first /diff/"+id+"/right");
        }
    }
}