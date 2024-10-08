package textprocview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;

import textproc.GeneralWordCounter;

public class BookReaderApplication {

    public static void main(String[] args) throws FileNotFoundException {

        //Scan in undantagsord.txt and create Set of stopwords
        Scanner scan = new Scanner(new File("undantagsord.txt"), "UTF-8");
		Set<String> stopwords = new HashSet<String>();

		while(scan.hasNext()){
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		scan.close();

        //Creating GeneralWordCounter based on stopwords
		GeneralWordCounter generalProcessor = new GeneralWordCounter(stopwords);

        //Optional: Use JFileChooser to chose filename
        JFileChooser fileChooser = new JFileChooser("C:");
        int r = fileChooser.showOpenDialog(null);
        
        if(r == JFileChooser.APPROVE_OPTION){
            Scanner s = new Scanner(new File(fileChooser.getSelectedFile().getAbsolutePath()), "UTF-8");
            s.findWithinHorizon("\uFEFF", 1);
		    s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		    while (s.hasNext()) {
			    String word = s.next().toLowerCase();
			    generalProcessor.process(word);
            }
            s.close();
        }
        else{
            //Scan in nilsholg.txt and send to GeneralWordCounter to process file
            Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
            s.findWithinHorizon("\uFEFF", 1);
		    s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
            while (s.hasNext()) {
			    String word = s.next().toLowerCase();
			    generalProcessor.process(word);
            }
            s.close();
        }

		

        BookReaderController BRC = new BookReaderController(generalProcessor);
        
    }
}
