public class Interface{
    public static void main(String[] args){
        Name zooty = new Name("Zooty", "Nooters");
        Address z1 = new Address("Wallaby Way","42","Sydney", "2774");
        Person p1 = new Person(zooty,z1);
        System.out.println(z1);
        System.out.println(p1);
    }
    
}
