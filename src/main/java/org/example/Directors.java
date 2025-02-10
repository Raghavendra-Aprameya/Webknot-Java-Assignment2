package org.example;

public class Directors {
    private int id;
    private String name;
    private String dob;
    private String nationality;

    public Directors(int id,String name,String dob,String nationality)
    {
        this.id=id;
        this.name=name;
        this.dob=dob;
        this.nationality=nationality;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public String getDob(){return dob;}
    public String getNationality(){return  nationality;}

    @Override
    public  String toString()
    {
        return id + "," + name + "," + dob + "," + nationality;
    }
}
