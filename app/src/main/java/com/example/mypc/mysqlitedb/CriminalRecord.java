package com.example.mypc.mysqlitedb;

/**
 * Created by MY PC on 26-03-2018.
 */

public class CriminalRecord
{
    int id;
    String name;
    String disp;
    String city;


    public CriminalRecord()
    {

    }

    public CriminalRecord(int id, String name, String disp, String city)
    {
        this.id = id;
        this.name = name;
        this.disp = disp;
        this.city = city;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDisp(String disp)
    {
        this.disp = disp;
    }

    public void setCity(String city)
    {
        this.city = city;
    }


    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDisp()
    {
        return disp;
    }

    public String getCity()
    {
        return city;
    }
}
