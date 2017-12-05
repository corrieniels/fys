package com.mycompany.ezfinder3;

public class Language {
    
    private int id;
    private String name;
    
    public Language(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return String.valueOf(id);
    }
    
}
