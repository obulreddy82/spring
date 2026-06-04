package com.rajtechnologies;

public class HelloWorld {

    private String message;

    public void getMessage() {
        System.out.println("Your message is: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init(){
        System.out.println("Bean is going to be initialized... ");
    }

    public void destroy(){
        System.out.println("Bean is going to be destroyed... ");
    }
}
