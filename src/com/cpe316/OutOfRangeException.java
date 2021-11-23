package com.cpe316;

public class OutOfRangeException extends Exception{
    public OutOfRangeException(){
        this("");
    }
    public OutOfRangeException(String message){
        super(message);
    }

}
