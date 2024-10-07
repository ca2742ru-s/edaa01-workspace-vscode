package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {

		long t0 = System.nanoTime();
		
		TextProcessor nils = new SingleWordCounter("nils");
		TextProcessor norge = new SingleWordCounter("norge");
		TextProcessor multiProcessor = new MultiWordCounter(REGIONS);

		Scanner scan = new Scanner(new File("undantagsord.txt"), "UTF-8");
		Set<String> stopwords = new HashSet<String>();

		while(scan.hasNext()){
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		scan.close();

		TextProcessor generalProcessor = new GeneralWordCounter(stopwords);
		

		List<TextProcessor> list = new ArrayList<TextProcessor>();
		list.add(0, nils);
		list.add(1, norge);

		Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor tp : list){
				tp.process(word);
				multiProcessor.process(word);
			}
			generalProcessor.process(word);

		}

		s.close();

		/* 
		for(TextProcessor tp : list){
			tp.report();
		}
			*/
		multiProcessor.report();
		
		//generalProcessor.report();

		long t1 = System.nanoTime();
		System.out.println("Tid: " + (t1 - t0)/1000000 + " ms");
	}
}