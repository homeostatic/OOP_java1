package model;

public abstract class Block {
    
    boolean passable;
    
    // by default the "special" flag will be "None"
    String special = "None";


    public boolean isPassable() {
        return passable;
    }


    public String getSpecial() {
        return special;
    }

    

    
}
