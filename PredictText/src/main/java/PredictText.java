import java.io.*;

public class PredictText {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "/home/antchil/MEGA/MEGAsync/data";
        String pathTrie = "/home/antchil/MEGA/MEGAsync/trie.ser";
        Trie trie = new Trie(fileName);
        System.out.println(trie.predictText("ab"));
    }
}
