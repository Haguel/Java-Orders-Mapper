package dev.haguel.exception;

import java.io.IOException;

public class DirectoryNotFoundException extends IOException {
    public DirectoryNotFoundException() {
        super();
    }

    public DirectoryNotFoundException(String message) {
        super(message);
    }

    public DirectoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
