import java.io.*;

public class PredictText {
    public static void saveTrie(Trie trie, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(trie);
        oos.close();
    }
    public static Trie loadTrie(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Trie result = (Trie) ois.readObject();
        ois.close();
        return result;
    }
    public static void WriteObjectToFile(Trie trie, String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(trie);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "/home/antchil/MEGA/MEGAsync/data";
        String pathTrie = "/home/antchil/MEGA/MEGAsync/trie.ser";
        Trie trie = new Trie(fileName);
//        Trie trie = loadTrie(pathTrie);
        WriteObjectToFile(trie,pathTrie);
        System.out.println(trie.predictText("ab"));
    }
}
