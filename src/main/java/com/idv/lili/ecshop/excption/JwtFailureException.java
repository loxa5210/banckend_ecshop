package com.idv.lili.ecshop.excption;

public class JwtFailureException extends Exception {
	public JwtFailureException(String message) {
		super(message);
	}
}
