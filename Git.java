import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

class Git {
    public static void main(String[] args) {
        try {
            init();
            File test = new File("test.txt");
            test.createNewFile();
            System.out.println(hash(test.getPath()));
        } catch (IOException e) {
            return;
        }      
    }

    public static void init() throws IOException {
        File git = new File("git");
        File objects = new File("git/objects");

        boolean doesEverythingExist = true;
        
        if (git.mkdir() == true) {
            doesEverythingExist = false;
        }

        if (objects.mkdir() == true) {
            doesEverythingExist = false;
        }

        File index = new File("git/index");
        File head = new File("git/HEAD");

        if (index.createNewFile() == true) {
            doesEverythingExist = false;
        }

        if (head.createNewFile() == true) {
            doesEverythingExist = false;
        }

        if (doesEverythingExist == true) {
            System.out.println("Git Repository Already Exists");
        } else {
            System.out.println("Git Repository Created");
        }
    }

    public static String hash(String filePath) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }

            // deletes last new line
            if (text.length() > 1) {
                text.delete(text.length() - 1, text.length());
            }

            br.close();

            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(text.toString().getBytes()); 
            String hashString = HexFormat.of().formatHex(hash);

            return hashString;

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
            return "";
        }
    }
}