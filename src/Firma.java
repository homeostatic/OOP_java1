import java.util.Scanner;

public class Firma extends AbstractContact {
    private Contact founder;
    private String FirmName;

    Firma(Contact founder, String firmName, Address address) {
        this.founder = founder;
        this.FirmName = firmName;
        this.setAddress(address);
    }
    Firma(String firmName, Address address) {
        this.FirmName = firmName;
        this.setAddress(address);
        
    }

    Contact getFounder() {
        return founder;
    }
    void setFounder(Contact founder) {
        this.founder = founder;
    }
    String getFirmName() {
        return FirmName;
    }
    void setFirmName(String firmName) {
        FirmName = firmName;
    }
    static Firma scanFirma(Scanner scan){
        // Step 1: Founder details
			System.out.println("Founder details: ");
			Contact founder = Contact.scanContact(scan);
			// Step 2: Company Name
			System.out.println("Enter Company Name: ");
			String companyName = scan.nextLine();
			//Step 3: Company Address
			Address companyAddress = Address.scanAddress(scan);
			Firma firma = new Firma(founder, companyName,companyAddress);
            return firma;

    }

    public String toString() {
        return String.format("%s, %s, %s", this.FirmName.toString(), this.founder.getName().toString() ,this.getAddress().toString());
    }


    
}
