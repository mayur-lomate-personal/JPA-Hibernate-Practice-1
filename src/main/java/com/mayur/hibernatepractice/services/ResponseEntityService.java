package com.mayur.hibernatepractice.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityService {
	
	public static ResponseEntity<?> getResponseEntity(Object data, String message, int status) {
		Map<Object, Object> body = new HashMap<Object, Object>();
		body.put("data", data);
		body.put("message", message);
		body.put("status", status);
		body.put("timestamp", LocalDateTime.now());
		return ResponseEntity.ok(body);
	}
}
