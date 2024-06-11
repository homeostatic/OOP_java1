import java.util.Scanner;

// Class for storing first and last names of people


public class Name {
    private String first;
    private String last;

    Name(String firstname, String lastname){
        this.first = firstname;
        this.last = lastname;
    }
  
    
    /** 
     * @return String
     */
    String firstname(){
        return this.first;
    }
    String lastname(){
        return this.last;
    }

    static Name addName(Scanner scan){
        System.out.println("Enter the first name:");
			String f = scan.nextLine();
			System.out.println("Enter the last name:");
			String l = scan.nextLine();
			
			Name name = new Name(f,l);
            return name;
    } 
    public String toString(){
        return String.format("%s %s", this.first,this.last);
    }
}
