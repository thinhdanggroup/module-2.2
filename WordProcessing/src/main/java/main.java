import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class main {

//    public static ArrayList<String> ListFile(String directory) {
//        ArrayList<String> listFile = new ArrayList<String>();
//        File folder = new File(directory);
//        File[] listOfFiles = folder.listFiles();
//
//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                listFile.add(file.getName());
//                System.out.println(file.getName());
//            }
//        }
//        return listFile;
//    }

    public static void main(String[] args) throws IOException {
//        System.out.println(ListFile("/home/thinda/Downloads/blogs"));
        long startTime = System.currentTimeMillis();
        String fileName = "/home/thinda/Downloads/blogs";
        Dictionary a = new Dictionary(fileName);
//        DictionaryBF a = new DictionaryBF(fileName);
        String key ="programs";
        System.out.println("Searching " + key + ": "+ a.contains(key));
        System.out.println((System.currentTimeMillis() - startTime));

    }
}
