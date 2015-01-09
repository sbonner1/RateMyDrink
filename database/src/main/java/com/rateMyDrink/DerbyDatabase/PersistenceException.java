package com.rateMyDrink.DerbyDatabase;

/**
 * Created by shanembonner on 1/8/15.
 */
public class PersistenceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersistenceException(String msg) {super(msg);}

    public PersistenceException(String msg, Throwable cause) {super(msg, cause);}
}