package com.ucab.ucab_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginResponse {
    @NotNull(message = "Success flag is required")
    private boolean success;

    @NotBlank(message = "Message is required")
    @Size(max = 255, message = "Message must not exceed 255 characters")
    private String message;

    private Object data; // Can be String (for MFA code) or MiembroSesionDTO

    // Constructors
    public LoginResponse() {}

    public LoginResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}