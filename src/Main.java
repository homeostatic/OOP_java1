import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    
    public static void main(String[] args){
        // Scanner is initiallised to read user inputs as needed, will be passed to the interactive 'constructor' methods
        Scanner scan = new Scanner(System.in);

        // Names and addresses make up personal contacts
        Name elmo = new Name("Elmo", "");
        Address elmAddress = new Address("Seasam Straße","2","New York", "12345");
        Contact p1 = new Contact(elmo,elmAddress);

        Contact p2 = new Contact(new Name("Big", "Bird"),new Address("Seasam Staße","3" , "New York", "12345"));

        Name sn = new Name("Sam", "Tagart");
        Name jn = new Name("Janna", "Krüger");

        Address sa = new Address("Holtenauer Straße", 201, "Kiel", 24105); 
        Address ja = new Address("Olshausen Straße", 123, "Kiel", 24116); 
    
        Contact janna = new Contact(jn, ja);
        Contact sam = new Contact(sn, sa);

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
        B.add(p2);
        B.add(p1);
        B.add(sam);
        B.add(janna);
        // works for firmen too.
        B.add(muppets); 
        // the arraylist of <AbstractContact> can now be passed to the Addressbuch constructor
        Adressbuch A = new Adressbuch(B);


        A.search("Wally");
        A.deleteContact(scan);
        A.printContacts();
        //A.printContacts();
        scan.close();
    }
    
}
