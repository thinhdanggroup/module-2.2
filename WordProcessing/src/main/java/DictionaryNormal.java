import java.io.*;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Dictionary {
    //    private BufferedReader br;
    static ArrayList<String> data = new ArrayList<String>();

    public Dictionary(String filePath) throws IOException {
        ArrayList<String> allFileDir= listAllFileDir(filePath);
        for (String name:  allFileDir) {
            saveWordFile(filePath +"/"+name);
        }
//        System.out.println(data);
//        for (String s : data) {
//            System.out.println(s);
//        }
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

    private void saveWordFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "UTF8"));
        String str = null;
        while ((str = br.readLine()) != null) {

            if (str.contains(" ")) {
                List<String> li = getWords(str);
                for (String a : li) {
//                    System.out.println(a);
                    data.add(a);
                }
            }
        }
        br.close();
    }

    public static List<String> getWords(String text) {
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();

        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex));
            }
        }

        return words;
    }

    public boolean contains(String target) throws IOException {
        String str = null;
        boolean result = false;
        long startTime = System.nanoTime();
        for (String s : data) {
            if (target.equals(s)) {
                result =  true;
            }
        }
        System.out.println(System.nanoTime()-startTime);
        return result;
    }
}
