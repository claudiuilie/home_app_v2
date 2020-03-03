package com.cinema.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "login")
@RequestScoped
public class LoginBean {

    private String username = null;
    private String password = null;
    private String result;

    public String getResult() {
        return result;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void submit(){
        System.out.println("Submitted, password: "+ this.password + " username: "+ this.username );
        this.result = "Login success.";
    }

}
