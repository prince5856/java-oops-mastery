package com.prep.m11_exceptions.exercises;

/**
 * Exercise 1 [I]: Checked and Unchecked Exceptions
 */
public class Ex1_ExceptionHandling {

    static class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }

        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // TODO: Create a method that throws checked exception
    public static void riskyMethod() throws CustomException {
        throw new UnsupportedOperationException("TODO");
    }

    // TODO: Create a method that throws unchecked exception
    public static void unsafeMethod() {
        throw new UnsupportedOperationException("TODO");
    }
}
