public class Main {
    public static void main(String[] args) {
        Cryptor cryptor=new Cryptor();
        System.out.println(cryptor.cryptCesar("ABCDEFGHIJKÖ", 3, 1));
        System.out.println(cryptor.cryptCesar("DEFGHIJKLMNB", 3, -1));
    }
}
