public class Main {
    public static void main(String[] args) {
        Cryptor cryptor=new Cryptor();
        System.out.println(cryptor.cryptCesar("_HA:LLÖ", 3, 1));
        System.out.println(cryptor.cryptCesar("KDOOB_:", 3, -1));
    }
}
