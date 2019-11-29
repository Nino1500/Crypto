public class Main {
    public static void main(String[] args) {
        Cryptor cryptor=new Cryptor();
        System.out.println(cryptor.cryptCesar("ABCD:EFG_HIJKÖ", 3, 1));
        System.out.println(cryptor.cryptCesar("DEFGHIJKLMNB:_", 3, -1));
        System.out.println(cryptor.cryptVigenere("The quick brown fox jumps over 13 lazy dogs.", "cryptii", 1));
        System.out.println(cryptor.cryptVigenere("Vyc fnqkm spdpv nqo hjfxa qmcg 13 eiha umvl.", "cryptii", -1));
    }
}
