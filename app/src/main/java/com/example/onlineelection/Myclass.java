package com.example.onlineelection;

public class Myclass {
    private String Name;
    private int Id;
    public Myclass (){

    }
    public Myclass(String Name, int Id){
        this.Name = Name;
        this.Id =Id;

     }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

