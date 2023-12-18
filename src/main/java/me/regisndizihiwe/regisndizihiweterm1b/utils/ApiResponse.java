package me.regisndizihiwe.regisndizihiweterm1b.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
public class ApiResponse <T> {
    public  int status;
    public  String message;
    public  boolean success;
    public T data;
}
