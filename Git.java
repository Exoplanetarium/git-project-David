import java.io.File;
import java.io.IOException;

class Git {
    public static void main(String[] args) {
        try {
            initRepo();
        } catch (IOException e) {
            return;
        }
    }

    public static void initRepo() throws IOException {
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
}