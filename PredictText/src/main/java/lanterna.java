
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * The forth tutorial, introducing the GUIScreen interface
 *
 * @author Martin
 */
public class lanterna {
    public static void saveTrie(Trie trie, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(trie);
        oos.close();
    }
    public static Trie loadTrie(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Trie result = (Trie) ois.readObject();
        ois.close();
        return result;
    }
    public static void main(String[] args) {
//    public static void data(){
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        String fileName = "/home/antchil/MEGA/MEGAsync/data";
        String pathTrie = "/home/antchil/MEGA/MEGAsync/trie.ser";
        try {
//            long startTime = System.currentTimeMillis();
//            final Trie trie = new Trie(fileName);
            final Trie trie = loadTrie(pathTrie);
//            System.out.println(System.currentTimeMillis() - startTime);
            screen = terminalFactory.createScreen();
            screen.startScreen();
            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            final Window window = new BasicWindow("Predict Text");

            Panel contentPanel = new Panel(new GridLayout(2));

            GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
            gridLayout.setHorizontalSpacing(3);

            Label title = new Label("This is a text prediction");
            title.setLayoutData(GridLayout.createLayoutData(
                    GridLayout.Alignment.BEGINNING, // Horizontal alignment in the grid cell if the cell is larger than the component's preferred size
                    GridLayout.Alignment.BEGINNING, // Vertical alignment in the grid cell if the cell is larger than the component's preferred size
                    true,       // Give the component extra horizontal space if available
                    false,        // Give the component extra vertical space if available
                    2,                  // Horizontal span
                    1));                  // Vertical span
            contentPanel.addComponent(title);

            contentPanel.addComponent(new Label("Your word: "));
            final TextBox wordTxt = new TextBox()
                    .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER));
            contentPanel.addComponent(wordTxt);

//            contentPanel.addComponent(new Label("Password Box (right aligned)"));
//            contentPanel.addComponent(
//                    new TextBox()
//                            .setMask('*')
//                            .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.END, GridLayout.Alignment.CENTER)));

//            contentPanel.addComponent(new Label("Read-only Combo Box (forced size)"));
//            List<String> timezonesAsStrings = new ArrayList<String>();
//            for(String id: TimeZone.getAvailableIDs()) {
//                timezonesAsStrings.add(id);
//            }
//            ComboBox<String> readOnlyComboBox = new ComboBox<String>(timezonesAsStrings);
//            readOnlyComboBox.setReadOnly(true);
//            readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));
//            contentPanel.addComponent(readOnlyComboBox);
//
//            contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
//            contentPanel.addComponent(
//                    new ComboBox<String>("Item #1", "Item #2", "Item #3", "Item #4")
//                            .setReadOnly(false)
//                            .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));

            /*
            Some user interactions, like buttons, work by registering callback methods. In this example here, we're
            using one of the pre-defined dialogs when the button is triggered.
             */
            final Label txt1 =new Label("");
            contentPanel.addComponent(new Label(""));
            contentPanel.addComponent(new Button("Button", new Runnable() {
                @Override
                public void run() {
                    ArrayList<String> arrayText = trie.predictText(wordTxt.getText());
                    String printText = "Don't have your key word in my database";
                    if (arrayText != null) {
                        printText = "";
                        int i = 0;
                        for (String str : arrayText) {
                            printText += str + "\n";
                            i+=1;
                            if (i>=5) break;
                        }
                    }
                    txt1.setText(printText);
//                    MessageDialog.showMessageDialog(textGUI, "You want to search ", printText, MessageDialogButton.OK);
                }
            }).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER, GridLayout.Alignment.CENTER)));

            /*
            Close off with an empty row and a separator, then a button to close the window
             */

            contentPanel.addComponent(txt1);
            contentPanel.addComponent(new Label(""));
            contentPanel.addComponent(
                    new EmptySpace()
                            .setLayoutData(
                                    GridLayout.createHorizontallyFilledLayoutData(2)));
            contentPanel.addComponent(
                    new Separator(Direction.HORIZONTAL)
                            .setLayoutData(
                                    GridLayout.createHorizontallyFilledLayoutData(2)));
            contentPanel.addComponent(
                    new Button("Close", new Runnable() {
                        @Override
                        public void run() {
                            window.close();
                        }
                    }).setLayoutData(
                            GridLayout.createHorizontallyEndAlignedLayoutData(2)));

            window.setComponent(contentPanel);


            textGUI.addWindowAndWait(window);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (screen != null) {
                try {
                    screen.stopScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}