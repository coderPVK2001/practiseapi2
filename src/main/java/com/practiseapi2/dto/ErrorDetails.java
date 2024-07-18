package com.practiseapi2.dto;

import java.util.Date;

public class ErrorDetails {

    private String message;

    private Date date;

    private String webrequest;

    public ErrorDetails(Date date, String message, String webrequest) {
        this.date = date;
        this.message = message;
        this.webrequest = webrequest;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getWebrequest() {
        return webrequest;
    }
}
