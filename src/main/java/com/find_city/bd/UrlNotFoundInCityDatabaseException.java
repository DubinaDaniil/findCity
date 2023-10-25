package com.find_city.bd;

public class UrlNotFoundInCityDatabaseException extends Exception {

    public UrlNotFoundInCityDatabaseException (String msg) {
        super(msg);
    }
}
