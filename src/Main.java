import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
       Cryptor cryptor=new Cryptor();
        System.out.println(cryptor.hash("SHA", "Test"));
        System.out.println(cryptor.hash("SHA512", "Test"));
        System.out.println(cryptor.hash("SHA256", "Test"));
        System.out.println(cryptor.hash("SHA3", "Test"));
        System.out.println(cryptor.hash("MD5", "Test"));
        if(cryptor.areEqual("Hallo", "Halloo")){
            System.out.println("Equal");
        }
        else {
            System.out.println("Nope");
        }

    }
}