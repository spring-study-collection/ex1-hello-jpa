package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        //2. 래퍼 클래스 or String
//        Integer a = new Integer(10);
//        Integer b = a; //레퍼런스 값이 복사됨 -> 같은 인스턴스 공유
//
//        //a의 값을 변경하면 b도 바뀜 (근데 값을 변경할 수 있는 방법 x)
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);


        //1. 자바 기본 타입은 절대 공유 x
//        int a = 10;
//        int b = a; //값을 복사함
//
//        a = 20;
//
//        System.out.println("a = " + a); //20
//        System.out.println("b = " + b); //10
    }
}
