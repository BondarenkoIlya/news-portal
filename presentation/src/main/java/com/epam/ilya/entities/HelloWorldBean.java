package com.epam.ilya.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "helloBean" , eager = true)
@SessionScoped
public class HelloWorldBean {

    private String message;

    public String getMessage() {
        return "Hello епта";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
