import java.io.*;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class DictionaryNormal implements Dictionary {
    static HashSet<String> storage = new HashSet<String>();
    WordHandle wordHandle;
    public DictionaryNormal(String filePath) throws IOException {
        wordHandle = new WordHandle();
        ArrayList<String> allFileDir= wordHandle.listAllFileDir(filePath);
        int i=0;

        for (String name:  allFileDir) {
            System.out.println(i++);
            insertWord(filePath +"/"+name);
        }
    }
    public void insertWord(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "UTF8"));
        String str = null;
        while ((str = br.readLine()) != null) {
            if (str.contains(" ")) {
                List<String> li = wordHandle.getWords(str);
                for (String a : li) {
                    if (!storage.contains(a) ){
                        storage.add(a);
                    }
                }
            }

        }
        br.close();
    }
    public boolean contains(String target) throws IOException {
        String str = null;
        boolean result = false;
        for (String s : storage) {
            if (target.equals(s)) {
                result =  true;
            }
        }
        return result;
    }
}
