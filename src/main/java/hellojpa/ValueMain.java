package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        //기본값 타입 비교
        int a = 10;
        int b = 10;

        System.out.println("a == b: " + (a == b)); //true

        //임베디드(객체) 비교
        Address address1 = new Address("city", "street", "10000");
        Address address2 = new Address("city", "street", "10000");

        System.out.println("address1 == address2: " + (address1 == address2)); //false
        System.out.println("address1 equals address2: " + (address1.equals(address2))); //true
    }
}
