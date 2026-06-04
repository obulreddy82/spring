package com.rajtechnologies;

public class Passport {
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    private String passportNumber;

    public String getPassport(){
        System.out.println("Passport is called"+passportNumber);
        return passportNumber;
    }
}
