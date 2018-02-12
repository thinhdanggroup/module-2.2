import java.io.IOError;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrieNode implements Serializable {
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
        isEnd = true;
    }

    public boolean containsKey(char ch) {
        try {
            boolean isContain =links[ch -'a'] != null;
            return isContain;
        }
        catch (Exception e) {
            System.out.println("error");
        }
        return false;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        try {
            links[ch -'a'] = node;
            isEnd = false;
        }
        catch (Exception e) {
            System.out.println(ch);
        }
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
    public ArrayList<Character> getAllValidKey() {
        ArrayList<Character> data = new ArrayList<Character>();
        for (int i =0 ; i< R;i++) {
            if (links[i] != null) {
                data.add((char) ('a' + i));
            }
        }
        return data;
    }
}
