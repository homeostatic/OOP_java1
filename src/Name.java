public class Name {
    private String first;
    private String last;
    public Name(String firstname, String lastname){
        this.first = firstname;
        this.last = lastname;
    }
    public String firstname(){
        return this.first;
    }
    public String lastname(){
        return this.last;
    }
    public String toString(){
        return String.format("%s %s", this.first,this.last);
    }
}
