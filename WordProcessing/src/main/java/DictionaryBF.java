    import orestes.bloomfilter.BloomFilter;
    import orestes.bloomfilter.FilterBuilder;

    import java.io.*;
    import java.lang.reflect.Array;
    import java.text.BreakIterator;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.regex.Pattern;

    public class DictionaryBF implements Dictionary {
        WordHandle wordHandle;
        BloomFilter<String> storage = new FilterBuilder(1000*1000, 0.01).buildBloomFilter();
        public DictionaryBF(String filePath) throws IOException {
            wordHandle = new WordHandle();
            ArrayList<String> allFileDir= wordHandle.listAllFileDir(filePath);
            for (String name:  allFileDir) {
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
                        storage.add(a);
                    }
                }
            }
            br.close();
        }
        public boolean contains(String target) throws IOException {
            long startTime = System.nanoTime();
            boolean result = storage.contains(target);
            System.out.println(System.nanoTime()-startTime);
            return result;
        }
    }
