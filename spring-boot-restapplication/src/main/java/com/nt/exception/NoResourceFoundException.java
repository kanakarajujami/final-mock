package com.nt.exception;

public class NoResourceFoundException extends RuntimeException{

    public String resourceName;
    public String fieldName;
    public Long fieldValue;

    public NoResourceFoundException(  String resourceName,String fieldName,Long fieldValue) {

        super(String.format("%s not found with %s:'%s'",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;

    }
}
