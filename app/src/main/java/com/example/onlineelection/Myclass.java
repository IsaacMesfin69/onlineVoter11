package com.example.onlineelection;

public class Myclass {
    private String Name;
    private String ID;
    public Myclass (){

    }
    public Myclass(String Name, String Id){
        this.Name = Name;
        this.ID =Id;

     }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        ID = id;
    }
}

