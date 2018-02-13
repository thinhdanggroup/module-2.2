import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
//        System.out.println(ListFile("/home/thinda/Downloads/blogs"));

        String fileName = "/home/antchil/MEGA/MEGAsync/blogs";
        Dictionary dictionary = new DictionaryHashset(fileName);
//        Dictionary dictionary = new DictionaryBF(fileName);



        String[] keys = {"apple","java","mail","google","VNG","good","wonderful","nothing","haha","nice"};

        int mean_duration = 0;
        for (String key: keys){
            // Calc time to find key in Dictionary
            long startTime = System.nanoTime();
            if (dictionary.contains(key)) {
                System.out.println("Key " + key + " exist in database");
            } else {
                System.out.println(key + " not found");
            }
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            System.out.println("Duration: " +duration + " ns");
            mean_duration += duration;
        }
        System.out.println("Mean duration: " + (mean_duration/keys.length) + " ns");

        //        code for test a word
        while (true) {
            System.out.println("You want to search: ");
            Scanner scanner = new Scanner(System.in);
            String key = scanner.nextLine();
            if (key.equals("1")){
                break;
            }
            long startTime = System.nanoTime();
            if (dictionary.contains(key)) {
                System.out.println("Key exist in database");
            } else {
                System.out.println("Not found");
            }
            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            System.out.println("Duration: " +duration + "ns");
        }
    }
}
