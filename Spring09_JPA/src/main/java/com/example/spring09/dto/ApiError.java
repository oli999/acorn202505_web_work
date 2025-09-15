package com.example.spring09.dto;

import java.util.Map;

public record ApiError(String code, Map<String, String> errors) {}