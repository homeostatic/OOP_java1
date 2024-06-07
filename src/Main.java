import java.util.ArrayList;
import java.util.Scanner;

public class Main{


    // example useage of the Addressbuch and it's related classes 
    
    public static void main(String[] args){

        
        // Scanner is initiallised to read user inputs as needed, will be passed to the interactive addX() methods
        Scanner scan = new Scanner(System.in);

        // Names and addresses make up personal contacts
        Name elmo = new Name("Elmo", "");
        Address elmAddress = new Address("Seasam Straße","2","New York", "12345");
        Contact person1 = new Contact(elmo,elmAddress);

        Contact person2 = new Contact(new Name("Big", "Bird"),new Address("Seasam Staße","3" , "New York", "12345"));

        Name sn = new Name("Sam", "Tagart");
        Name jn = new Name("Janna", "Krüger");

        Address sa = new Address("Holtenauer Straße", 201, "Kiel", 24105); 
        Address ja = new Address("Olshausen Straße", 123, "Kiel", 24116); 
    
        Contact janna = new Contact(jn, ja);
        Contact sam = new Contact(sn, sa);

        // Compnies have a company name (String attribute of the Firma-class), and may have a linked founder (a personal contact) as well as a company address.
        Name kerName = new Name("Kermit", "the Frog");
        Contact kermit = new Contact(kerName);
        Firma muppets =new Firma(kermit, "Muppets inc.", elmAddress);

        // addressbooks can be initallised empty and added to iteratively
        Adressbuch testbook = new Adressbuch();
        // ...using the interactive console tool
        testbook.addContact(scan);
        
        // or an arraylist of contact objects can be passed to the construstor directly
        ArrayList<AbstractContact> B = new ArrayList<AbstractContact>();
        // adding our examples to the arraylist
        B.add(person2);
        B.add(person1);
        B.add(sam);
        B.add(janna);
        // works for firmen too.
        B.add(muppets); 
        // the arraylist of <AbstractContact> can now be passed to the Addressbuch constructor
        Adressbuch A = new Adressbuch(B);

        // search returns all contacts with a (partial) match in any field to the search string
        A.search("Sesam");

        // the delete tool is implimented though an interactive menu in the console
        A.deleteContact(scan);

        // all contacts can be displayed at once
        A.printContacts();
        
        
        scan.close();
    }
    
}
