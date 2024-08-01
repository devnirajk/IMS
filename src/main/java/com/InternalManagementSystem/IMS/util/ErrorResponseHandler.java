package com.InternalManagementSystem.IMS.util;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ErrorResponseHandler {
    public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatus status){
        Map<String, String> errorResponse = new HashMap<String, String>();

        errorResponse.put("message", message);
        errorResponse.put("statusCode", (String.valueOf(status.value())));

        return new ResponseEntity<Object>(errorResponse, status);
    }
}