package com.nt.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDetails {

    public String date;
    public String path;
    public String description;
}