public class Main {
    public static void main(String[] args) {
        Cryptor cryptor=new Cryptor();
        System.out.println(cryptor.cryptCesar("ABCD:EFG_HIJKÖ", 3, 1));
        System.out.println(cryptor.cryptCesar("DEFGHIJKLMNB:_", 3, -1));
        System.out.println(cryptor.cryptVigenere("I am in : Tokyo", "YEN", 1));
        System.out.println(cryptor.cryptVigenere("DEZDRD:_::)JOIW", "YEN", -1));
        // ABC D (3) EFGH I (8) JKL M (12) NOPQRSTU V (21) WX Y (24) ZÄ Ö Ü
    }
}