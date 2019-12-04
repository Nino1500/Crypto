import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Cryptor {

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ"; // we don't care about performance here!
	
	// ******************* Aufgabe 1 Beginn *****************************************
	/** 
	*  En- or decrypts the given inString with the given key based on mode
	*  @param inString: The string to be en- or decrypted
	*  @param key:	The key use for en- or decryption
	*  @param mode: Either 1 or -1. 1 selects eincryption and -1 selects decryption
	*  @return The en- or dectypted text
	*/
	public String cryptCesar(String inString, int key, int mode) {

		inString=inString.toUpperCase(); //setting all letters to uppercase

		String content= ""; //String for content (which is sorted from special characters

		StringBuilder special_chars= new StringBuilder(); // Stringbuilder for special characters

		for (int i = 0; i < inString.length(); i++) {
			if(alphabet.contains(String.valueOf(inString.charAt(i)))){
				content+=(String.valueOf(inString.charAt(i)));
			}
			else {
				special_chars.append(String.valueOf(inString.charAt(i)));
			}
		}

		StringBuilder final_content=new StringBuilder(); //A Stringbuilder for the final content

		int position=0;

		for (int i = 0; i < content.length(); i++) { //Looping through until the letter of content index is found in given alphabet
			for (int j = 0; j < alphabet.length(); j++) {
				if(content.charAt(i)==alphabet.charAt(j)){
					position=j; //saving position for swapping/filling up in a row
				}
			}
			if(mode == 1){
				final_content.append(alphabet.charAt(Math.floorMod(position+key, 29))); //encryption
			}
			else if(mode == -1) {
				final_content.append(alphabet.charAt(Math.floorMod(position-key, 29))); //decryption
			}
		}
		return (final_content.toString())+special_chars.toString(); //returning final content with special chars at the end
	}

	/** 
	*  En- or decrypts the given inString with the given key based on mode
	*  @param inString: The string to be en- or decrypted
	*  @param key:	The key use for en- or decryption
	*  @param mode: Either 1 or -1. 1 selects eincryption and -1 selects decryption
	*  @return The en- or dectypted text
	*/
	public String cryptVigenere(String inString, String key, int mode) {

		inString=inString.toUpperCase();

		StringBuilder special_chars= new StringBuilder();
		String container= "";
		for (int i = 0; i < inString.length(); i++) {
			if(alphabet.contains(String.valueOf(inString.charAt(i)))){
				container+=(inString.charAt(i));
			}
			else {
				special_chars.append(inString.charAt(i));
			}
		}
		inString=container;

		int move=0;
		int counter=0;
		String newKey="";

		for (int i = 0; i < inString.length(); i++) { //first we have to make a new key, the key has to repeat until length of original text is the same length
			if(move == key.length()){
				counter=0;
				move=0;
			}
			newKey = newKey + key.charAt(counter);
			counter++;
			move++;
		}

		StringBuilder final_content=new StringBuilder(); //final content

		int first=0; //key value	-
		int second=0; //letter value in alphabet

		for (int i = 0; i < newKey.length(); i++) {
			for (int j = 0; j < alphabet.length(); j++) {
				if(newKey.charAt(i)==alphabet.charAt(j)){
					first=j; //position of key in the alphabet
				}
			}
			for (int j = 0; j < alphabet.length(); j++) {
				if(inString.charAt(i)==alphabet.charAt(j)){
					second=j; //position of text in the alphabet
				}
			}
			if(mode == 1){ //encrypt
				final_content.append(alphabet.charAt(Math.floorMod(first+second, 29)));
			}
			else if(mode == -1){ //decrypt
				final_content.append(alphabet.charAt(Math.floorMod(second-first, 29)));
			}
		}

		return final_content.toString()+special_chars.toString();

	}

	// ******************* Aufgabe 1 Ende *****************************************

	// ******************* Aufgabe 2 Beginn *****************************************
	/** 
	*  Brute-force the encrypted string.
	*  @return All possible decrypted texts delimitted by a newline.
	*/	
	public String bruteForceCesar() {
		String encryptedMessage = "SXT ITÄF XEF PÄÄTE, IPE STD UPÄÄ XEF. SXT ITÄF XEF SXT VTEPÖFWTXF STD FPFEPRWTÜ, ÜXRWF STD SXÜVT. SXT ITÄF XEF SGDRW SXT FPFEPRWTÜ QTEFXÖÖF GÜS SPSGDRW, SPEE TE PÄÄT FPFEPRWTÜ EXÜS. STÜÜ, SXT VTEPÖFWTXF STD FPFEPRWTÜ QTEFXÖÖF, IPE STD UPÄÄ XEF GÜS PGRW, IPE PÄÄTE ÜXRWF STD UPÄÄ XEF. SXT FPFEPRWTÜ XÖ ÄAVXERWTÜ DPGÖ EXÜS SXT ITÄF. SXT ITÄF LTDUMÄÄF XÜ FPFEPRWTÜ. TXÜTE ZPÜÜ STD UPÄÄ ETXÜ ASTD ÜXRWF STD UPÄÄ ETXÜ GÜS PÄÄTE OQDXVT VÄTXRW QÄTXQTÜ. IPE STD UPÄÄ XEF, SXT FPFEPRWT, XEF SPE QTEFTWTÜ HAÜ EPRWHTDWPÄFTÜ. STD EPRWHTDWPÄF XEF TXÜT HTDQXÜSGÜV HAÜ VTVTÜEFMÜSTÜ. (EPRWTÜ, SXÜVTÜ.) TE XEF STÖ SXÜV ITETÜFÄXRW, STD QTEFPÜSFTXÄ TXÜTE EPRWHTDWPÄFTE ETXÜ LG ZNÜÜTÜ. XÜ STD ÄAVXZ XEF ÜXRWFE LGUMÄÄXV: ITÜÜ SPE SXÜV XÖ EPRWHTDWPÄF HADZAÖÖTÜ ZPÜÜ, EA ÖGEE SXT ÖNVÄXRWZTXF STE EPRWHTDWPÄFTE XÖ SXÜV QTDTXFE BDMYGSXLXTDF ETXÜ.";
		StringBuilder translatedBuild = new StringBuilder();
		for (int i = 0; i < 29; i++) {
			translatedBuild.append("Key: "+i+" "+" Message: "+cryptCesar(encryptedMessage,i,-1)+"\n");
		}
		return translatedBuild.toString();
	}

	/** 
	*  Decrypt cesar encrypted string using the letter-frequency method
	*  @return The key used for encryption
	*/	
	public int letterfreqCesar() {
		String encryptedMessage ="WÖX MXBJ ÖIJ TBBXI, MTI WXH YTBB ÖIJ. WÖX MXBJ ÖIJ WÖX ZXITCJÄXÖJ WXH JTJITVÄXD, DÖVÄJ WXH WÖDZX. WÖX MXBJ ÖIJ WKHVÄ WÖX JTJITVÄXD UXIJÖCCJ KDW WTWKHVÄ, WTII XI TBBX JTJITVÄXD IÖDW. WXDD, WÖX ZXITCJÄXÖJ WXH JTJITVÄXD UXIJÖCCJ, MTI WXH YTBB ÖIJ KDW TKVÄ, MTI TBBXI DÖVÄJ WXH YTBB ÖIJ. WÖX JTJITVÄXD ÖC BEZÖIVÄXD HTKC IÖDW WÖX MXBJ. WÖX MXBJ PXHYQBBJ ÖD JTJITVÄXD. XÖDXI ATDD WXH YTBB IXÖD EWXH DÖVÄJ WXH YTBB IXÖD KDW TBBXI SUHÖZX ZBXÖVÄ UBXÖUXD. MTI WXH YTBB ÖIJ, WÖX JTJITVÄX, ÖIJ WTI UXIJXÄXD LED ITVÄLXHÄTBJXD. WXH ITVÄLXHÄTBJ ÖIJ XÖDX LXHUÖDWKDZ LED ZXZXDIJQDWXD. (ITVÄXD, WÖDZXD.) XI ÖIJ WXC WÖDZ MXIXDJBÖVÄ, WXH UXIJTDWJXÖB XÖDXI ITVÄLXHÄTBJXI IXÖD PK ARDDXD. ÖD WXH BEZÖA ÖIJ DÖVÄJI PKYQBBÖZ: MXDD WTI WÖDZ ÖC ITVÄLXHÄTBJ LEHAECCXD ATDD, IE CKII WÖX CRZBÖVÄAXÖJ WXI ITVÄLXHÄTBJXI ÖC WÖDZ UXHXÖJI FHQÜKWÖPÖXHJ IXÖD.";
		int key=0;
		char[] char_array=encryptedMessage.toCharArray();
		HashMap<Character, Integer> map=new HashMap<>();
		for (int i = 0; i < char_array.length; i++) {
			if(alphabet.contains(String.valueOf(char_array[i]))){
				if(map.containsKey(char_array[i])){
					Integer value=map.get(char_array[i]);
					map.replace(char_array[i], value, value+1);
				}
				else{
					map.put(char_array[i], 1);
				}
			}
		}
		Map.Entry<Character, Integer> maxEntry=null;
		Map.Entry<Character, Integer> secondMaxEntry=null;
		int counter=0;
		for(Map.Entry<Character, Integer> entry : map.entrySet()){
			counter++;
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue())>0){
				secondMaxEntry=maxEntry;
				maxEntry=entry;
			}
		}
		int pos1 = 0, pos2=0;
		for (int i = 0; i < alphabet.length(); i++) {
			if(maxEntry.getKey().equals(alphabet.charAt(i))){
				pos1=i;
			}
			if(secondMaxEntry.getKey().equals(alphabet.charAt(i))){
				pos2=i;
			}
		}
		key=pos1-pos2;
		if(key<0) key=key*-1;
		return key;
	}

	/** 
	*  Use "known plaintext" to decrypt vigenere encrypted text
	*  @return The key used for encryption
	*/	
	public String knownPlaintextVigenere() {
		String encrypted = "IVHI ZVEÖBJV XNCVN EDU ÖRHFEE, GIY ÄRBYÄ VS ÄKB ÄRITHUSYH XRD 'LÄUFEWUURRYÄ TOXR' WÜR UEI LZGYÄXFE PEUE NKTH TXBAEÄJ WÜBW MÜDJVLJ 'ÖNFGD PCNÖBTYHJ' UÄZFIZS HAKCQTHCVVY QE SRYPÄVN. ZFAKEBZEIR. EÜÄXF UÄIHECYKBG OXZ XRH NJN CDYB RBYB UED RGTYBHVIWUITHYÄ GHHTWEW CJVHK DIN ÄÖTHK CVHI ÖA NRZV. GÖV JVX SYUXB ÜCJ NÜPÄHS EDSRYPÄSAI. GEÜRD SÜR RCÖDIRM GXÜCÖR UAKRD SÜR KIY ZVSÜPÄVRK MCHÜÄ QBYBJFAÄRD. XRDB 'ÄKF NRÖÜ ÜPÄ PUBTBOÜQ SIE. YEÜCIH XNI NÜPÄH XNIG ÜPÄ NÜPÄH MRHWOCTJ WYBWV' (PI ÖLHVN NKTH VC SFÄZ VFÄ BIIFTBA - HEIBÖHOIVTÜ GVIGIETI - UJHPJ://WNG.MOLDKSE.PEA/NNJTH?F=SM6VP-I5ÖCE - TS 1:26). ÖB XVXGED IZNER KÜECVYE FÖVL CFRSJ CZT QXF BBODTFTHRPÖVX UEQ UED UFEWUXB MÖD CFQXG. ZÖH ZBXINXYÖTHYÄ XRTCIVN NDCNPZEI";
		String opener = "Sehr geehrte Damen und Herren";
		String end = "Mit freundlichen Grüssen Anonymous";
		String key = "";
		// TODO: find the key used for encryption using a known plaintext attack
		return key;
	}
	// ******************* Aufgabe 2 Ende *****************************************



	// ******************* Aufgabe 3 Beginn *****************************************
	public String hash(String hasher, String input) {
		String hashString = "";
		MessageDigest digest;
		switch(hasher) {
			case "MD5":
				try {
					digest = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e) {
					System.err.println("No such Algorithm");
				}
				break;
			case "SHA":
				// TODO: get Instance of MessageDigest with SHA Algorithm
				break;
			case "SHA256":
				// TODO: get Instance of MessageDigest with SHA-256 Algorithm
				break;
			case "SHA512":
				// TODO: get Instance of MessageDigest with SHA-512 Algorithm
				break;
			case "SHA3":
				// TODO: get Instance of MessageDigest with SHA3-512 Algorithm
				break;
		}
		// TODO: implement digest generation
		
		//TODO: byte mdByteData[] = 
				
		//hashString = byteArrayToHexString(mdByteData);
		return hashString;
	}
	
	public boolean areEqual(String first, String second) {
		// TODO: compare if two strings are equal
		return false;
	}
	// ******************* Aufgabe 3 Ende *****************************************
	
	// ******************* Aufgabe 4 Anfang *****************************************
	public String hashPassword(int mode, char[] password) {
		String passwordHash = "";
		//TODO: implement Password hashing with:
		// 	SHA-512 100.000 rounds and salt
		//	PBKDF2 witch HMAC-SHA256
		// 	the output should look like: <algo nr.>:<salt>:<rounds>:<hash> 
		//	where your sha512 implementation is alog nr 1 and the PBKDF2 is algo nr 2 (this is specified by mode)
		// don't forget to check if mode is either 1 or 2 (if not mode should be set to 1)
		return passwordHash;
	}
	
	public boolean verifyPassword(char[] passwordToVerify, String hashedPassword) {
		//TODO: implement password verification 
		return true;
	}
	// ******************* Aufgabe 4 Ende *****************************************
	
	// ******************* Aufgabe 5 Anfang *****************************************
	private String cryptAEAG(char[] password) {
		// TODO: implement AES authenticaed encryption and decryption
		return "AEAG";

	}
	// ******************* Aufgabe 5 Ende *****************************************

	private static String byteArrayToHexString(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    
	    for (int i = 0; i < hash.length; i++) {
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    	if(hex.length() == 1) hexString.append('0');
	    	hexString.append(hex);
	    }
	    return hexString.toString();
	}
}

