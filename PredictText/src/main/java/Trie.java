import java.io.*;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Trie implements Serializable {
    private TrieNode root;
    public Trie(String filePath) throws IOException {
        root = new TrieNode();
        ArrayList<String> allFileDir = listAllFileDir(filePath);
        int i =0 ;
        for (String name : allFileDir) {
            i+=1;
            long startTime = System.currentTimeMillis();
            saveWordFile(filePath + "/" + name);
            System.out.println(i + " : " + (System.currentTimeMillis() - startTime));
        }
    }

    private ArrayList<String> listAllFileDir(String path) {
        ArrayList<String> listFile = new ArrayList<String>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                listFile.add(file.getName());
//                System.out.println(file.getName());
            }
        }
        return listFile;
    }

    //    Read contain of file
    private void saveWordFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "UTF8"));
        String str = null;
        int i = 0;
        List<String> store = new ArrayList<String>();
        while ((str = br.readLine()) != null) {
            if (str.contains(" ")) {
                List<String> li = getWords(str);
//                List<String> li_without_duplicate = new ArrayList<>(new HashSet<>(li));
//                for (String a : li_without_duplicate) {
//                    insert(a.toLowerCase());
//                }
            }
            if ((i++)%100000==0) {
                System.out.println("line : " + i);
            }

        }
        br.close();
    }

    // Get word from lines
    public List<String> getWords(String text) {
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();

        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                String word = text.substring(firstIndex, lastIndex);
                if (word.matches("[a-zA-Z0-9]*") && !word.contains("\'") && !word.matches(".*\\d+.*")) {
                    insert((text.substring(firstIndex, lastIndex)).toLowerCase());
                }
            }
        }
        return words;
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
//        System.out.println(word);
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
//        node.setEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        node.getAllValidKey();
        return node != null;
    }

    public void getAllWord(TrieNode parent, String word,ArrayList<String>  predictedWord) {
        ArrayList<Character> allCharNode = parent.getAllValidKey();
        if (allCharNode != null) {
            if (parent == null || parent.isEnd()) {
//                System.out.println(word);
                predictedWord.add(word);
            } else {
                for (int i = 0; i < allCharNode.size(); i++) {
                    char curLetter = allCharNode.get(i);
//                    System.out.println(word + curLetter);
                    getAllWord(parent.get(curLetter), word + curLetter,predictedWord);
                }
            }
        }
    }

    public ArrayList<String> predictText(String prefix) {
        TrieNode node = root;
        String word = "";
        ArrayList<String>  predictedWord = new ArrayList<String>();
        for (int i = 0; i < prefix.length(); i++) {
            char curLetter = prefix.charAt(i);
            word+=curLetter;
            if (node.containsKey(curLetter)) {
//                node.getAllValidKey();
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        if (node != null) {
            getAllWord(node, word, predictedWord);
        }
        return predictedWord;
    }
}
