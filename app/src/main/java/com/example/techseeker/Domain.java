package com.example.techseeker;

public class Domain {
    private String title;
    private int image ;
    private  int technicianTotal;

    public Domain(){

    }

    public Domain(String title, int image) {
        this.title = title;
        this.image = image;

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title= title;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image= image;
    }

    public int getTechnicianTotal() {
        return technicianTotal;
    }

    public void setTechnicianTotal(int technicianTotal) {
        this.technicianTotal = technicianTotal;
    }
}
