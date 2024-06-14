package model;

public class Path extends Block {

    /**
     * default constructor for regular path blocks
     */
    public Path(){
        this.passable = true;
        this.special = "None";
    }

    /**
     * special constructor for making pathblocks with extra functions
     * @param special: String containing the type of special function
     */
    public Path(String special){
        this.passable = true;
        this.special = special;
    }

}