package textprocview;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import textproc.GeneralWordCounter;

public class BookReaderController {
    
    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 750, 500));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        
        //Create container to store panel
        Container pane = frame.getContentPane();
        
        //Create JPanel to fit Buttons and Text fields
        JPanel southPanel = new JPanel();

        //Creating JButtons
        //JButton alphabeticButton = new JButton("Alphabetical order");
        //JButton frequencyButton = new JButton("Frequency order");
        JButton search = new JButton("Search");

        //Optional: Creating JRadioButtons, JButton and JFileChooser
        JRadioButton alphabeticButton = new JRadioButton("Alphabetical order");   //Optional JRadioButtons for alphabetical sorting
        JRadioButton frequencyButton = new JRadioButton("Frequency order");       //Optional JRadioButtons for frequency sorting

        //Creating JTextField
        JTextField textField = new JTextField(10);

        //Adding buttons and text fields to JPanel
        southPanel.add(alphabeticButton);
        southPanel.add(frequencyButton);
        southPanel.add(textField);
        southPanel.add(search);
        
        //Add panel to pane
        pane.add(southPanel, BorderLayout.SOUTH);


        //Creating a wordList that has filtered out keys of integers
        List<Entry<String, Integer>> wordList = counter.getWordList();
        wordList.removeIf(entry -> Character.isDigit(entry.getKey().charAt(0))); //Remove all integer Keys

        //Creating a ListModel based on the sorted wordList
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(wordList);
        
        //Link model (SortedListModel) to view (JList)
        JList<Map.Entry<String, Integer>> listView = new JList<Map.Entry<String, Integer>>(listModel);

       //Sort listModel in alphabetical order if Alphabetic button is pressed
       alphabeticButton.addActionListener(event -> { 
        frequencyButton.setSelected(false);   //Optional for JRadioButton
        listModel.sort((entry1, entry2) -> {
            int result = entry1.getKey().compareTo(entry2.getKey());
            return result;    
            });
        });
       
        //Sort listModel in frequency order if Frequency button is pressed
        frequencyButton.addActionListener(event -> {
            alphabeticButton.setSelected(false);  //Optional for JRadioButton
            listModel.sort((entry1, entry2) -> {
                int result = entry2.getValue() - entry1.getValue();
                if(result == 0){
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                else{
                    return result;
                }        
            });
        });

        /**Find word in listView. If current word from textView is in listView, highlight it in the listView.
         * If word is not found, show a JOptionPane popup window with not found message.
         * **/
        search.addActionListener(event -> {
            boolean found = false;
            for(int i = 0; i < listModel.getSize(); i++){
                if(listModel.getElementAt(i).getKey().equals(textField.getText().trim().toLowerCase())){
                    listView.ensureIndexIsVisible(i);
                    listView.setSelectedIndex(i);
                    found = true;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(pane, "Word does not exist");
            }
        });

        textField.addActionListener(event -> {
            search.doClick();
        });

        //Creating JScrollPane and adding it to pane
        JScrollPane scrollPane = new JScrollPane(listView);
        pane.add(scrollPane);

        //frame.pack();
        frame.setVisible(true);

    }


}
