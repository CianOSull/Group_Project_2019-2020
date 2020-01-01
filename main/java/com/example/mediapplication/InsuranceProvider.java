package com.example.mediapplication;

public class InsuranceProvider {
    private String Name;
    private Object ID;

    public InsuranceProvider(String name, String ID) {
        Name = name;
        this.ID = ID;
    }

    public InsuranceProvider() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getID() {
        return ID;
    }

    public void setID(Object ID) {
        this.ID = ID;
    }
}
