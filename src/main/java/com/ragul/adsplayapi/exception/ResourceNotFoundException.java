package com.ragul.adsplayapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private Boolean success;
    private int code;
    private String message;

    public ResourceNotFoundException(String resourceName, String value) {
        super(String.format("%s not found with %s", resourceName, value));
        this.message = String.format("%s not found with %s", resourceName, value);
        this.success = false;
        this.code = HttpStatus.NOT_FOUND.value();
    }


}
