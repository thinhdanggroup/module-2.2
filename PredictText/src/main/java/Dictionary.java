//import orestes.bloomfilter.BloomFilter;
//import orestes.bloomfilter.FilterBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Dictionary {
    //    private BufferedReader br;
//    static ArrayList<String> data = new ArrayList<String>();
//    BloomFilter<String> bloomFilter = new FilterBuilder(1000*1000, 0.01).buildBloomFilter();
//    public Dictionary(String filePath) throws IOException {
//        ArrayList<String> allFileDir= listAllFileDir(filePath);
//        for (String name:  allFileDir) {
//            saveWordFile(filePath +"/"+name);
//        }
//    }
//
//    private ArrayList<String> listAllFileDir(String path) {
//        ArrayList<String> listFile = new ArrayList<String>();
//        File folder = new File(path);
//        File[] listOfFiles = folder.listFiles();
//
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                listFile.add(file.getName());
////                System.out.println(file.getName());
//            }
//        }
//        return listFile;
//    }
//
//    private void saveWordFile(String fileName) throws IOException {
//        BufferedReader br = new BufferedReader(
//                new InputStreamReader(
//                        new FileInputStream(fileName), "UTF8"));
//        String str = null;
//        while ((str = br.readLine()) != null) {
//            if (str.contains(" ")) {
//                List<String> li = getWords(str);
//                for (String a : li) {
//                    bloomFilter.add(a);
//                }
//            }
//        }
//        br.close();
//    }
//
//    public static List<String> getWords(String text) {
//        List<String> words = new ArrayList<String>();
//        BreakIterator breakIterator = BreakIterator.getWordInstance();
//
//        breakIterator.setText(text);
//        int lastIndex = breakIterator.first();
//        while (BreakIterator.DONE != lastIndex) {
//            int firstIndex = lastIndex;
//            lastIndex = breakIterator.next();
//            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//                words.add(text.substring(firstIndex, lastIndex));
//            }
//        }
//
//        return words;
//    }
//
//    public boolean contains(String target) throws IOException {
//        boolean result = bloomFilter.contains(target);
//        return result;
//    }
}
