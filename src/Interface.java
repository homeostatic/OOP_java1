import java.util.ArrayList;
import java.util.Scanner;

public class Interface{

    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Name zooty = new Name("Zooty", "Nooters");
        Address z1 = new Address("Wallaby Way","42","Sydney", "2774");
        Contact p1 = new Contact(zooty,z1);
        Contact p2 = new Contact();
        p2.setAddress(new Address("Seasam Staße","3" , "New York", "12345"));
        p2.setName(new Name("Big", "Bird"));
        Name sn = new Name("Sam", "Tagart");
        Name jn = new Name("Janna", "Krüger");
        Address sa = new Address("Holtenauer Straße", 201, "Kiel", 24105); 
        Address ja = new Address("Olshausen Straße", 123, "Kiel", 24116); 
        
        Contact janna = new Contact(jn, ja);
        Contact sam = new Contact(sn, sa);
        ArrayList<Contact> B = new ArrayList<Contact>();
        B.add(p2);
        B.add(p1);
        B.add(sam);
        B.add(janna);
        // System.out.println(z1);
        // System.out.println(p1);
        // System.out.println(p2);
        Adressbuch A = new Adressbuch(B);
        //Contact c1 = Contact.contactScan(scan);
        //A.addContact(c1);
        //A.printContacts();
        Contact c2 = Contact.contactScan(scan);
        A.addContact(c2);
        //A.printContacts();
        A.search("Wallaby");
        A.printContacts();
        scan.close();
    }
    
}
