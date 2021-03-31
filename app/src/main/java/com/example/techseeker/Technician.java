package com.example.techseeker;

public class Technician {
    private String photo;
    private String fullName;
    private String email;
    private String phone;
    private String location;
    private String domain;

    public Technician(){

    }

    public Technician(String photo,String fullName, String email, String phone, String location, String domain) {
        this.photo = photo;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.domain = domain;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
