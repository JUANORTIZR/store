package com.juandevs.prue11.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    String message;
    boolean status;
    T object;

    public Response(String message, boolean status, T object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }
}
