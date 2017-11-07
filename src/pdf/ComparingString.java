/*
 * ComparingString.java
 *
 * Created on November 13, 2008, 8:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/*
 * ComparingString.java
 *
 * Created on August 28, 2008, 10:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

import UserInterfaceDesign.LoadStopWord;
import java.io.IOException;

/**
 *
 * @author mar
 */
public class ComparingString {
    
    private String stopWords;
    
    private String[] ds;
    
    private String[] stpwords;
    
    private int count_Of_Words;
    
    private String[] wordstp;
    
    private String[] ds_Word;
    
    private String[] ds_PDF;
    private String[] ds_Word_Word;
    
    /** Creates a new instance of ComparingString */
    public ComparingString(String filename) throws IOException {
      //  LoadStopWord lsw=new LoadStopWord();
     //  String  filename=lsw.get_Filename();
        
        stop_Word_Reader swr=new stop_Word_Reader();
        stopWords=swr.Reader();
        DOCRead doc=new DOCRead();
        
        Parser p=new Parser();
        ds_Word= doc.readDocFile(filename);
        //String [] pdfArray=ext.split("\\s");
        ds_Word_Word= doc.readDocFile(filename);
        //ds=stopWords.split("\\s");
        
    }
   public boolean Is_Stop_Word_WORD(String word) throws IOException {
      
        Parser p=new Parser();
        
        //p.writer(stopWords[0]);
        String a="";
        String s="";
        //System.out.println(ds[1]);
        word=word.trim();
        for (int i = 0; i < ds_Word_Word.length; i++) {
            
            
            wordstp=ds_Word_Word[i].split("\\s");
            try{
                for (int j = 0; j < wordstp.length; j++) {
                    a=wordstp[j];
                    a=a.trim();
                    
                    if(a.equalsIgnoreCase(word)==true||Equal_Word(a,word)){
                        System.out.println("sa \n"+word+"\n");
                        //s+=word+" ";
                        return true;
                    }
                    
                    
                    
                    
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        //p.writer(s);
        
        return false;
    }
    
    public boolean Is_Stop_Word(String word) throws IOException {
    	word=word.trim();
        //p.writer(stopWords[0]);
        String a=ds_Word[0];
        String s="";
        //System.out.println(ds[1]);
        for (int i = 0; i < ds_Word.length; i++) {
            
            
            wordstp=ds_Word[i].split("\\s");
            try{
                for (int j = 0; j < wordstp.length; j++) {
                    a=wordstp[j];
                    a=a.trim();
                    
                    if(a.equalsIgnoreCase(word)==true||Equal_Word(a,word)){
                        System.out.println("sa \n"+word+"\n");
                        s+=word;
                        return true;
                    }
                    
                    
                    
                    
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        // p.writer(s);
        
        return false;
    }
    public boolean Is_Stop_Word_PDF(String word) throws IOException {
        stopWords=stopWords.trim();
        word=word.trim();
        ds_PDF=stopWords.split(",\\s*" );
        Parser p=new Parser();
        //p.writer(stopWords[0]);
        String a=ds_PDF[0];
        
        // String[]w=ds[1].split("\\s");
        String s="";
        // System.out.println(ds[1]);
        try{
            for (int j = 0; j < ds_PDF.length; j++) {
                a=ds_PDF[j];
                a=a.trim();
                if(a.equalsIgnoreCase(word)==true||Equal_Word(a,word)){
                    System.out.println("sa \n"+word+"\n");
                    s+=word;
                    return true;
                }
                
                
                
                
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        // p.writer(s);
        
        return false;
    }
    public String[] get_Words(String str) {
        int c=0;
        str=str.trim();
        String[] stw=str.split(",\\s*" );
        count_Of_Words=0;
        for (int i = 0; i < stw.length; i++) {
            if(stw[i]!=null){
                char[]temp=stw[i].toCharArray();
                for (int j = 0; j < temp.length; j++) {
                    if(temp[j]=='|'&&j!=c) {
                        stpwords[count_Of_Words++]=stw[i].substring(c,j);
                        c=j;
                    }
                }
            }
            
        }
        return stpwords;
    }
    
   
    public boolean Equal_Word(String word1,String word2)
    {
    	boolean Flag=true;
    	if(word1.length()!=word2.length())return false;
    	for (int i = 0; i < word1.length()&&i<word2.length(); i++) {
    		
			if(word1.codePointAt(i)==word2.codePointAt(i))
				Flag=true;
			else if((word1.codePointAt(i)==1610&&word2.codePointAt(i)==1740)||(word2.codePointAt(i)==1610&&word1.codePointAt(i)==1740))
			{
				Flag=true;
			}
			else return false;
		}
    	return Flag;
    }
    
    
    
    
}
