package ru.kpfu.itis.javaLab.web.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Safin Ramil on 12.06.17
 * RamilSafNab1996@gmail.com
 */
@ResponseStatus(reason = "Can't load image")
public class StorageException extends RuntimeException {

    public StorageException() {
        super();
    }

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}
