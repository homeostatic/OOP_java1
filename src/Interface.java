public class Interface{
    public static void main(String[] args){
        Name zooty = new Name("Zooty", "Nooters");
        Address z1 = new Address("Wallaby Way","42","Sydney", "2774");
        Contact p1 = new Contact(zooty,z1);
        Contact p2 = new Contact();
        p2.setAddress(new Address("Seasam Staße","3" , "New York", "12345"));
        p2.setName(new Name("Big", "Bird"));
        System.out.println(z1);
        System.out.println(p1);
        System.out.println(p2);
    }
    
}
