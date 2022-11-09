package com.epam.cicddemo.models.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ApiResponse<T> {

    private String message;
    private Boolean success;
    private T object;
    private List<T> objectList;


    public ApiResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String message, Boolean success, T object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public ApiResponse(String message, Boolean success, List<T> objectList) {
        this.message = message;
        this.success = success;
        this.objectList = objectList;
    }
}
