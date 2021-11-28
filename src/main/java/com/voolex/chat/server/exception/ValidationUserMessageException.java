package com.voolex.chat.server.exception;

/**
 * Исключение, возникающее при ошибке валидации UserMessage
 */
public class ValidationUserMessageException extends RuntimeException {
    public ValidationUserMessageException(String message) {
        super(message);
    }
}
