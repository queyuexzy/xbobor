package com.exception;

@SuppressWarnings("serial")
public class NotLoginException extends Exception{
	public NotLoginException(){
	}

	public NotLoginException(String message){
		super(message);
	}
}
