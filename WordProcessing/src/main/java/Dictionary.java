import java.io.IOException;

public interface Dictionary {
    boolean contains(String target) throws IOException;
    void insertWord(String fileName) throws IOException;
}
