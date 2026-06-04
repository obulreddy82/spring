package com.rajtechnologies;

public class Customer {
    private String name;
    private Passport passport;

    public Passport getPassport() {
        return passport;
    }
//Setter for passport to instantiate the passport
    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber(){
        System.out.println("Passport Number is called"+passport.getPassportNumber());
        return passport.getPassportNumber();
    }
}
