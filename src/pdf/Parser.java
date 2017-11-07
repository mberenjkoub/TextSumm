/*
 * Parser.java
 *
 * Created on August 24, 2008, 12:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;


import java.util.regex.Pattern;
import org.apache.poi.hwpf.sprm.ParagraphSprmCompressor;
import java.awt.Font;
import org.faceless.pdf2.*;
import org.xml.sax.SAXException;

import Stemming.Stemmer;

import java.io.*;
import java.util.*;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.security.*;

import java.awt.Color;
/**
 *
 * @author mar
 */
public class Parser {
    
    /** Creates a new instance of Parser */
    
    private int count_Of_Words=0;
    
    private StringBuffer keyWords;
    
    private int[] score_Of_Para;
    
    private int[] score_Of_Word;
    
    private int count_Of_Word;
    
    
    
    
    public Parser() {
        
    }
    public  String parsing_Words(String text) throws IOException {
       
        Search s=new Search();
        extractor e=new extractor();
        String str="";
        String s1="";
        paragraphs h=new paragraphs();
        
        score_Of_Word = new int[ 10000];
        score_Of_Para = new int[ 1000 ];
        for (int i = 0; i < score_Of_Word.length; i++)
            score_Of_Word[i]=1;
        ComparingString cmp=new ComparingString("");
        //ds_PDF=stopWords.split(",\\s*" );
        StringBuffer matn=new StringBuffer("");
        String[] paragraph_Word = text.split( "\\u000A"); // split on commas
        paragraphs[] p1=new paragraphs[paragraph_Word.length];
        StringBuffer parakey=new StringBuffer();
        keyWords=new StringBuffer("");
        int count=0;
        Stemmer stm=new Stemmer();
        p1[count]=new paragraphs();
        p1[count].setparag_Word("");
        try{
            for (int i = 0; i<paragraph_Word.length; i++) {
                if(paragraph_Word[i]!=null&&!paragraph_Word[i].isEmpty()||!paragraph_Word[i].equals("")){//&&paragraph_Word[i].endsWith(".")){
                    
                    
                    //paragraph_Word[i]=paragraph_Word[i].trim();
                    
                    count_Of_Word=0;
                    
                    String[] word_Of_Paragraph=paragraph_Word[i].split("\\s");
                    if(count_Of_Word==0&&h.Split(paragraph_Word[i])==true){//count_Of_Word==0&&(word_Of_Paragraph[j].equals(".")||word_Of_Paragraph[j].equals(":"))){
                        System.out.println("dddooooooooooooooooooooooooooooootttttt");
                        
                        matn.insert(0,paragraph_Word[i]);
                        p1[count].setparag_Word(matn.toString());
                        p1[++count]=new paragraphs();
                        p1[count].setparag_Word("");
                         matn=new StringBuffer("");
                    } else
                    	matn = matn.insert(0, paragraph_Word[i]);
                    //p1[count].text+=paragraph_Word[i];
                    
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = 0; j < word_Of_Paragraph.length; j++) {
                        StringBuffer reverseWord=new StringBuffer(word_Of_Paragraph[j]).reverse();
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            // h.Split(word_Of_Paragraph[count_Of_Word]);
                            //  else if(count_Of_Word==0&&word_Of_Paragraph[j].equals(".")==false)
                            // p1[count].text=paragraph_Word[i];
                            count_Of_Word++;
                           // stm.Stemming_Function(word_Of_Paragraph[j]);
                            if(cmp.Is_Stop_Word_PDF(word_Of_Paragraph[j])==false&&cmp.Is_Stop_Word(word_Of_Paragraph[j])==false)//reverseWord.toString())==false)
                            {
                                parakey.append(word_Of_Paragraph[j]).append(' ');
                                
                                str=str.concat(reverseWord.toString()).concat(" ");
                                
                                if( (s.Search_Keywords(word_Of_Paragraph[j],keyWords.toString(),score_Of_Word))==false){
                                    keyWords.append(word_Of_Paragraph[j]).append(' ');
                                    
                                    
                                }
                                // Search(word_Of_Paragraph[j],paragraph_Word);
                            }
                            
                            // System.out.println("word"+word_Of_Paragraph[j]+"\t" );
                        }
                        
                    }
                    
                    str=str.concat("\n");
                    keyWords.append("\n");
                    parakey.append("\n");
                    System.out.println("\n");
                    
                    
                }
                
            }
            // u.Writer(str);
            
            // writer(str);
          /*  String [] w=keyWords.toString().split("\\s");
            for (int i = 0; i < w.length; i++) {
                if(!w[i].equals("\n")&&w[i]!=null)
                    s1+="score "+w[i]+"  "+score_Of_Word[i]+"\n";
            }
            writer(s1);*/
        }catch(Exception e1) {
            System.out.println(e1.getMessage());
        }
        s.get_Score(parakey.toString(),score_Of_Para,score_Of_Word,p1,count);//s,count);
        str=s.Selection_Sort(p1);
        return str;
    }
   /* public void Search(String word,String[] paragraphs) {
    
        int num_Of_Para=0;
        score_Of_Para = new int[ paragraphs.length ];
    
        try{
            for (int i = 0; i<paragraphs.length; i++) {
                if(paragraphs[i]!=null){
                    //paragraph_Word[i]=paragraph_Word[i].trim();
                    String[] word_Of_Paragraph=paragraphs[i].split("\\s");
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = 0; j < word_Of_Paragraph.length; j++) {
    
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            if(word_Of_Paragraph[j].equalsIgnoreCase(word)==true) {
                                score_Of_Para[num_Of_Para]++;
                            }
    
                            // System.out.println("word"+word_Of_Paragraph[j]+"\t" );
                        }
                    }
                    System.out.println("score"+score_Of_Para[num_Of_Para]);
    
                    num_Of_Para++;
    
                    System.out.println("\n");
                }
    
            }
    
        }catch(Exception e1) {
            System.out.println(e1.getMessage());
        }
    
    
    
    }*/
    public static void writer(String s)throws IOException {
        // mydb db=new mydb();
        PDF pdf = new PDF();
        s.trim();
        char [] c=s.toCharArray();
        int count=0;
        int j=0;
        char[]temp=s.toCharArray();
        
        
        PDFPage page = pdf.newPage("A4");
        
        PDFStyle mystyle = new PDFStyle();
        OpenTypeFont otf = new OpenTypeFont(new FileInputStream("E:\\times.ttf"),2);
        mystyle.setFont(otf, 12);
        mystyle.setFillColor(Color.black);
        
        
        String st="";
        
        
        page.setStyle(mystyle);
        //  if(s.equals(st.copyValueOf(c)))
        page.drawText(s+"\n\n\n", 10, page.getHeight()-100);
        //page.drawText("Hello, World");
        OutputStream fo = new FileOutputStream("Summary1.pdf");
        pdf.render(fo);
        System.out.println("sa");
        fo.close();
    }
}
