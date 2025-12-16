package com.seek.seleccion.util;

// Excepción personalizada
    public class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }