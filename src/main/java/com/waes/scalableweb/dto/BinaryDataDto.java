package com.waes.scalableweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * BinaryDataDto carries base64 binary data.
 * @author ttdduman
 */
public class BinaryDataDto {

    @NotNull
    @NotEmpty
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BinaryDataDto{" +
                "data='" + data + '\'' +
                '}';
    }
    public BinaryDataDto() {
    }
    public BinaryDataDto(@NotNull @NotEmpty String data) {
        this.data = data;
    }
}
