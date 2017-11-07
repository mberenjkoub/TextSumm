/*
 * DocParser.java
 *
 * Created on August 30, 2008, 9:26 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;
import UserInterfaceDesign.LoadStopWord;
import org.apache.poi.hwpf.sprm.ParagraphSprmCompressor;
import java.awt.Font;
import org.faceless.pdf2.*;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.*;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.security.*;
/**
 *
 * @author mar
 */
public class DocParser {
    private int count_Of_Words=0;
    
    private StringBuffer keyWords;
    
    private int[] score_Of_Para;
    
    private int[] score_Of_Word;
    
    private paragraphs[] p1;
    
    /** Creates a new instance of DocParser */
    public DocParser() {
    }
    public  String parsing_Words(String text) throws IOException {
        uni u=new uni();
        extractor e=new extractor();
        String str="";
        Parser p=new Parser();
        Search s=new Search();
        score_Of_Word = new int[ 10000];
        score_Of_Para = new int[ 1000 ];
        Writer_PDF wr=new Writer_PDF();
        LoadStopWord lsw=new LoadStopWord();
        ComparingString cmp=new ComparingString();
        String[] paragraph_Word = text.split( "\\n" ); // split on commas
        keyWords=new StringBuffer("");
        paragraphs[] p1=new paragraphs[paragraph_Word.length];
        
        StringBuffer parakey=new StringBuffer();
        try{
            for (int i = 0; i<paragraph_Word.length; i++) {
                if(paragraph_Word[i]!=null){
                    p1[i]=new paragraphs();
                    p1[i].setparag_Word(paragraph_Word[i]);
                    //paragraph_Word[i]=paragraph_Word[i].trim();
                    String[] word_Of_Paragraph=paragraph_Word[i].split("\\s");
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = 0; j < word_Of_Paragraph.length; j++) {
                        StringBuffer reverseWord=new StringBuffer(word_Of_Paragraph[j]).reverse();
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            if(cmp.Is_Stop_Word_WORD(word_Of_Paragraph[j])==false) {
                                str=str.concat(word_Of_Paragraph[j]).concat(" ");
                                // keyWords.append(word_Of_Paragraph[j]).append("\n");
                                parakey.append(word_Of_Paragraph[j]).append(' ');
                                
                                str=str.concat(reverseWord.toString()).concat(" ");
                                
                                if( (s.Search_Keywords(word_Of_Paragraph[j],keyWords.toString(),score_Of_Word))==false){
                                    keyWords.append(word_Of_Paragraph[j]).append(' ');
                                    
                                    
                                }
                            }
                            
                            // System.out.println("word"+word_Of_Paragraph[j]+"\t" );
                        }
                    }
                    str=str.concat("\n");
                    System.out.println("\n");
                }
                
            }
            //p.writer(str);
            u.Writer(str);
            // wr.Writer_Doc_2_PDF(str);
        }catch(Exception e1) {
            System.out.println(e1.getMessage());
        }
        
       // s.get_Score(parakey.toString(),score_Of_Para,score_Of_Word,p1);
        str=s.Selection_Sort(p1);
        return str;
    }
    
}
