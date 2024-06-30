import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class list {

    static private String accountf = "accounts.ser"; // file to store accounts
    private static String counterf = "counter.txt"; // we need to store the static count variable by itself.
    private static List<account> listofa = new ArrayList<account>();
    private list() {} // to make sure we do not create other objects of list.
    public static void storelistandcounter()
    {
        storelist();
        storecounter();
    }
    public static void loadlistandcounter()
    {
        loadlist();
        account.setObjectCount(loadcounter());
    }

    // TO ALLOW CLASSES TO ACCESS LIST
    public static List<account> getInstance() {
        return listofa;
    }
    private static void storelist()
    {
        try {
            FileOutputStream fo = new FileOutputStream(accountf);
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(listofa);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void storecounter() {
        try {
            FileWriter fw = new FileWriter(counterf);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(account.getObjectCount())); // to write the int in string from
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadlist()
    {
        try {
            FileInputStream fo = new FileInputStream(accountf);
            ObjectInputStream in = new ObjectInputStream(fo);
            listofa = (List<account>) in.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static int loadcounter() {
        try {
            FileReader fr = new FileReader(counterf);
            BufferedReader br = new BufferedReader(fr);
            int count = Integer.parseInt(br.readLine());
            br.close();
            return count;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("IO error", e);
        }
    }
    public static void initialize(String accountFilePath, String counterFilePath) { // this method is for testing
        accountf = accountFilePath;
        counterf = counterFilePath;
    }
}
