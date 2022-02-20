import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.*;

class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }





    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }



    public static ArrayList<ArrayList<String>> convertToDictionary(File encrypted, String secretKey) throws FileNotFoundException {
        ArrayList<ArrayList<String>> output = new ArrayList();                 

        Scanner encryptedReader = new Scanner(encrypted);

        int i = 0;
        while (encryptedReader.hasNext()){
            ArrayList<String> mid = new ArrayList<>();

            String temp = encryptedReader.nextLine();

            mid.add(temp.split(":")[0]);          //username
            String midTerm = AES.decrypt(temp.split(":")[1], secretKey);
            mid.add(midTerm.substring(0, midTerm.length() - temp.split(":")[2].length())); //password
            mid.add(temp.split(":")[2]);   //salt

            try{
                mid.add(temp.split(":")[3]);   //description
            }
            catch (Exception e){
                mid.add("");
            }

            output.add(mid);
        }

        return output;
    }
}