/*
 * absparser.java
 *
 * Created on September 9, 2008, 5:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Abstract;

import java.util.regex.Pattern;
import org.apache.poi.hwpf.sprm.ParagraphSprmCompressor;
import java.awt.Font;
import org.faceless.pdf2.*;
import org.xml.sax.SAXException;

import Stemming.Stemmer;
import Stemming.StemmingPart;

import java.io.*;
import java.util.*;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.security.*;

import java.awt.Color;
import pdf.ComparingString;
import pdf.DOCRead;
import pdf.Search;
import pdf.extractor;
import pdf.paragraphs;
import pdf.textword;


/**
 * 
 * @author mar
 */

/**
 * 
 * @author mar
 */
public class absparser {

	/** Creates a new instance of Parser */

	private int count_Of_Words = 0;

	private StringBuffer keyWords;

	private int[] score_Of_Para;

	private int[] score_Of_Word;

	private int count_Of_Word;

	private String[] abs_Word;

	private String[] abstract_Word;

	private paragraphs[] p1;

	private String[] paragraph_Word;

	private ComparingString cmp;

	private String abskeywords;

	private int index_Of_Bigin;

	private textword[] words;

	private int count_Of_absWord;

	private int count_Of_Bigin;

	private String[] Introduc_Word;

	private int count_Of_Para;

	public String parsing_Words(String filename,String text) throws IOException {
		boolean FINISHED = false;
		absSearch s = new absSearch();
		extractor e = new extractor();
		String str = "";
		String s1 = "";
		paragraphs h = new paragraphs();
		StemmingPart smp=new StemmingPart();
		
		score_Of_Word = new int[10000];
		score_Of_Para = new int[1000];
		for (int i = 0; i < score_Of_Word.length; i++)
			score_Of_Word[i] = 1;
		cmp = new ComparingString(filename);
		// ds_PDF=stopWords.split(",\\s*" );
		StringBuffer matn = new StringBuffer("");
		paragraph_Word = text.split("\\u000A"); // split on commas
		p1 = new paragraphs[paragraph_Word.length];
		StringBuffer parakey = new StringBuffer();
		keyWords = new StringBuffer("");
		DOCRead doc = new DOCRead();
		abs_Word = doc.readDocFile("e://abs.doc");
		abstract_Word = abs_Word[0].split("\\s");
		Introduc_Word = abs_Word[1].split("\\s");
		System.out.println("salam" + Introduc_Word[1]);
		int count = 0;
		Stemmer stm = new Stemmer();
		count_Of_Para = 0;
		words = new textword[1000];
		p1[count_Of_Para] = new paragraphs();
		p1[count_Of_Para].setparag_Word("");
		try {
			for (int i = 0; i < paragraph_Word.length; i++) {
				if (paragraph_Word[i] != null && !paragraph_Word[i].isEmpty()
						|| !paragraph_Word[i].equals("")) {// &&paragraph_Word[i].endsWith(".")){

					// paragraph_Word[i]=paragraph_Word[i].trim();

					count_Of_Word = 0;

					String[] word_Of_Paragraph = paragraph_Word[i].split("\\s");
					if (count_Of_Word == 0
							&& h.Split(paragraph_Word[i]) == true) {// count_Of_Word==0&&(word_Of_Paragraph[j].equals(".")||word_Of_Paragraph[j].equals(":"))){
						System.out
								.println("dddooooooooooooooooooooooooooooootttttt");

						matn.insert(0, paragraph_Word[i]);
						p1[count_Of_Para].setparag_Word(matn.toString());
						p1[++count_Of_Para] = new paragraphs();
						p1[count_Of_Para].setparag_Word("");
						matn = new StringBuffer("");
					} else
						// matn=matn.append(paragraph_Word[i]).append(' ');
						matn = matn.insert(0, paragraph_Word[i]);
					// p1[count].text+=paragraph_Word[i];

					// System.out.println("para"+paragraph_Word[i]+"\n");
					for (int j = word_Of_Paragraph.length - 1; j >= 0; j--) {
						StringBuffer reverseWord = new StringBuffer(
								word_Of_Paragraph[j]).reverse();
						if (!word_Of_Paragraph[j].isEmpty()
								|| !word_Of_Paragraph[j].equals("")) {
							count_Of_Word++;
							for (int f = 0; f < abstract_Word.length; f++) {

								if ((word_Of_Paragraph[j]
										.equalsIgnoreCase(abstract_Word[f]) == true)) {

									abskeywords = foundAbstract(i, count_Of_Para);
									i = index_Of_Bigin;
									FINISHED = true;

									// return abskeywords;
									break;
								}
							}

							if (FINISHED
									&& cmp
											.Is_Stop_Word_PDF(word_Of_Paragraph[j]) == false
									&& cmp.Is_Stop_Word(word_Of_Paragraph[j]) == false)// reverseWord.toString())==false)
							{
								// parakey.append(word_Of_Paragraph[j]).append('
								// ');

								// str=str.concat(reverseWord.toString()).concat("
								// ");
								
								
								
								/*
								String root = stm.Find_Root(word_Of_Paragraph[j]);
								*/
								String root =smp.dictionaryChecker(word_Of_Paragraph[j]);
								
								
								
								if (!root.isEmpty()
										|| !root.equals("")
										&& s.Search_Keywords(words,
												word_Of_Paragraph[j],
												count_Of_absWord) == true) {
									keyWords.append(word_Of_Paragraph[j])
											.append(' ');

								}
								// Search(word_Of_Paragraph[j],paragraph_Word);

							}

							// System.out.println("word"+word_Of_Paragraph[j]+"\t"
							// );

						}

					}
					// str+=abstractKeywords.toString();
					str = str.concat("\n");
					keyWords.append("\n");
					parakey.append("\n");
					System.out.println("\n");

				}

			}
			// u.Writer(str);

			// writer(str);
			/*
			 * String [] w=keyWords.toString().split("\\s"); for (int i = 0; i <
			 * w.length; i++) { if(!w[i].equals("\n")&&w[i]!=null) s1+="score
			 * "+w[i]+" "+score_Of_Word[i]+"\n"; } writer(s1);
			 */
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		matn.insert(0, paragraph_Word[paragraph_Word.length - 1]);
		p1[count_Of_Para].setparag_Word(matn.toString());

		s.get_Score(words, keyWords.toString(), p1, count_Of_Bigin,
				count_Of_absWord, count_Of_Para);// s,count);
		str = s.Selection_Sort(p1, count_Of_Para,count_Of_Bigin);
		// str=abstractKeywords.toString();

		return str;
	}

	public String foundAbstract(int i, int count) throws IOException {
		StemmingPart smp=new StemmingPart();
		StringBuffer abstractKeywords = new StringBuffer("");
		boolean FINISHED = false;
		absSearch s = new absSearch();
		Stemmer stm = new Stemmer();
		StringBuffer matn = new StringBuffer("");
		count_Of_absWord = 0;

		for (int k = i; k < paragraph_Word.length && !FINISHED; k++) {

			String[] word_Of_Paragraph = paragraph_Word[k].split("\\s");
			for (int a = 0; a < word_Of_Paragraph.length; a++) {
				StringBuffer reverseWord = new StringBuffer(
						word_Of_Paragraph[a]).reverse();

				if (!word_Of_Paragraph[a].isEmpty()
						|| !word_Of_Paragraph[a].equals("")) {
					if (cmp.Is_Stop_Word_WORD(word_Of_Paragraph[a]) == false)// reverseWord.toString())==false)
					{
						
						
						/*
						String root = stm.Find_Root(word_Of_Paragraph[a]);
						*/
						String root =smp.dictionaryChecker(word_Of_Paragraph[a]);
						
						
						
						
						if (!root.isEmpty() || !root.equals("")) {
							if (s
									.Search_Keywords(words, root,
											count_Of_absWord) == false) {
								words[count_Of_absWord] = new textword();
								words[count_Of_absWord].word = root;
								words[count_Of_absWord].score = 1;
								abstractKeywords.append(root).append(' ');
								count_Of_absWord++;

							}
						}
					}
					for (int g = 0; g < Introduc_Word.length; g++) {

						if (word_Of_Paragraph[a]
								.equalsIgnoreCase(Introduc_Word[g]) == true
								|| Introduc_Word[g]
										.equalsIgnoreCase(reverseWord
												.toString()) == true) {
							i = k;
							index_Of_Bigin = k;
							FINISHED = true;
							p1[count_Of_Para++]
									.setparag_Word(paragraph_Word[k]);

							p1[count_Of_Para] = new paragraphs();
							p1[count_Of_Para].setparag_Word("");
							matn = new StringBuffer("");
							count_Of_Bigin = count_Of_Para;
							return abstractKeywords.reverse().toString();

						}
					}
				}
			}
			// matn = matn.append(paragraph_Word[k]).append(' ');
			matn = matn.insert(0, paragraph_Word[k]);

		}
		p1[++count] = new paragraphs();
		p1[count].setparag_Word("");
		matn = new StringBuffer("");
		count_Of_Bigin = count;
		return abstractKeywords.reverse().toString();

	}

	public static void writer(String s) throws IOException {
		// mydb db=new mydb();
		PDF pdf = new PDF();
		s.trim();
		char[] c = s.toCharArray();
		int count = 0;
		int j = 0;
		char[] temp = s.toCharArray();

		PDFPage page = pdf.newPage("A4");

		PDFStyle mystyle = new PDFStyle();
		OpenTypeFont otf = new OpenTypeFont(
				new FileInputStream("E:\\times.ttf"), 2);
		mystyle.setFont(otf, 12);
		mystyle.setFillColor(Color.black);

		String st = "";

		page.setStyle(mystyle);

		// if(s.equals(st.copyValueOf(c)))
		page.drawText(s + "\n\n\n", 10, page.getHeight() - 100);
		// page.drawText("Hello, World");
		OutputStream fo = new FileOutputStream("Summary1.pdf");
		pdf.render(fo);
		System.out.println("sa");
		fo.close();
	}
}
