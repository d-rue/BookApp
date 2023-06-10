package de.drue.BookApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceInappropriateException extends RuntimeException{
    public ResourceInappropriateException(final String message){
        super(message);
    }
}
