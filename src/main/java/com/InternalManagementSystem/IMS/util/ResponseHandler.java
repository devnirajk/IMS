package com.InternalManagementSystem.IMS.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj){
        Map<String, Object> resMap = new HashMap<String, Object>();

        resMap.put("message", message);
        resMap.put("data", responseObj);
        resMap.put("statusCode", status.value());

        return new ResponseEntity<Object> (resMap, status);
    }
}