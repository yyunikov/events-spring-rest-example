package ua.yyunikov.integration;

public enum ErrorCode {
    FIELD_EMPTY("%s field should not be empty!");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

    public String createMessage(final String fieldName) {
        return String.format(message, fieldName);
    }
}
