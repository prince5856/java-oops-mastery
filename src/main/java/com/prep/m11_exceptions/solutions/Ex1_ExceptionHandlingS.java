package com.prep.m11_exceptions.solutions;

public class Ex1_ExceptionHandlingS {

    public static class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }

        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void riskyMethod() throws CustomException {
        throw new CustomException("Risky operation failed");
    }

    public static void unsafeMethod() {
        throw new RuntimeException("Unsafe operation");
    }
}
