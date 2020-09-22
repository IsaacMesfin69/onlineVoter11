package com.example.onlineelection;

public class Myclass {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String Name;
    private int ID;
    private String Image;

    public Myclass(String name, int ID, String image) {
        Name = name;
        this.ID = ID;
        Image = image;
    }


    public Myclass() {

    }
}




