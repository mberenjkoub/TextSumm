/*
 * Search.java
 *
 * Created on September 1, 2008, 2:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

import java.io.IOException;

/**
 *
 * @author mar
 */
public class Search {
    
    private int[] score_Of_Para;
    private String[] paragraphs;
    
    private String s;
    private Parser p=new Parser();
    
    private textword[] w;
    
    private int num_Of_Word;

    private int count;
    
    
    /** Creates a new instance of Search */
    public Search() {
        w=new textword[1000];
        num_Of_Word=0;
        
        s="";
        
    }
    
    
    public boolean Search_Keywords(String word,String keywords,int [] score_Of_Word) {
        String[] words=keywords.split("\\s");
        int num_Of_Para=0,k=0;
        try{
            
            for ( k = 0; k < num_Of_Word; k++) {
                if(word.equalsIgnoreCase(w[k].word)){
                    score_Of_Word[k]++;
                    w[k].setScore(score_Of_Word[k]);
                    System.out.println("score"+score_Of_Word[k]);
                    
                    return true;
                }
              /*  if(!words[k].isEmpty()||!words[k].equals("")){
                    if(words[k].equalsIgnoreCase(word)){
                        score_Of_Word[k]++;
                        w[k].setScore(score_Of_Word[k]);
               
                        System.out.println("score"+score_Of_Word[k]);
               
                        return true;
                    }
                }*/
                //   }
                //  s+="\n";
                // System.out.println("score"+score_Of_Para[num_Of_Para]);
                
                
            }
            
       /* try{
            for (int i = 0; i<paragraphs.length; i++) {
                if(paragraphs[i]!=null){
                    //paragraph_Word[i]=paragraph_Word[i].trim();
                    String[] word_Of_Paragraph=paragraphs[i].split("\\s");
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = 0; j < word_Of_Paragraph.length; j++) {
        
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            if(word_Of_Paragraph[j].equalsIgnoreCase(words[i])==true) {
                                score_Of_Para[k]++;
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
        }
        
        */
        }  catch(Exception e) {
            System.out.println(e.getMessage());
        }
        //score_Of_Word[k]++;
        if(k==1&&keywords.equals("")==true){
            w[num_Of_Word]=new textword();
            w[num_Of_Para].setWord(word,1);
        } else {
            w[num_Of_Word]=new textword();
            w[num_Of_Word].setWord(word,1);
        }
        num_Of_Word++;
        return false;
    }
    public void get_Score(String keywords,int [] score_Of_Para,int[] score_Of_Word,paragraphs[] p1,int c) throws IOException {
        //  p.writer(s);
        String s1="";
        this.count=c;
        //for (int i = 0; i < num_Of_Word; i++) {
        
        // s1+="score "+w[i].word+"  "+w[i].score+"\n";
        //}
        
        String []para=keywords.split("\\n");
        try{
            for (int i = 0; i <= count; i++) {
                if(p1[i].equals("")==false &&p1[i]!=null) {
                    s1+=p1[i]+"\n";
                    String[] words=p1[i].text.split("\\s");
                    
                    for (int j = 0; j < words.length; j++) {
                        if(!words[j].isEmpty()||!words[j].equals("")){
                            
                            for (int k = 0; k < num_Of_Word; k++) {
                                if(words[j].equalsIgnoreCase(w[k].word)){
                                    score_Of_Para[i]+=w[k].score;
                                    s1+="score "+w[k].word+"  "+w[k].score+"\n";
                                }
                            }
                            
                            
                        }
                        p1[i].setparag_Score(score_Of_Para[i]);
                        System.out.println("paragraph "+i+"scores "+score_Of_Para[i]+"\n");
                    }
                }
            }
            p.writer(s1);
        }
        
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public String Selection_Sort(paragraphs[] para1) {
        int Biggest=0;
        String output="";
        try{
            
            for (int i = 0; i < count-1; i++) {
                if(para1[i]!=null){
                Biggest=i;
                for (int j = i+1; j < count; j++) {
                    
                    if(para1[j]!=null&&para1[j].score > para1[Biggest].score)
                        Biggest=j;
                }
                //output+=para1[Biggest].text;
                swap(i,Biggest,para1);
                }
            }
            
            for (int i = 0; i < count; i++) {
                if(para1[i]!=null){
                    String[] word_Of_Paragraph=para1[i].text.split("\\s");
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = word_Of_Paragraph.length-1; j >=0; j--) {
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            if(word_Of_Paragraph[j].codePointAt(0)>=40&&word_Of_Paragraph[j].codePointAt(0)<=128&&word_Of_Paragraph[j].codePointAt(0)!=46&&word_Of_Paragraph[j].codePointAt(0)!=58)
                            
                            	output+=word_Of_Paragraph[j]+' ';
                            else{
                            StringBuffer reverseWord=new StringBuffer(word_Of_Paragraph[j]).reverse();
                            output+=reverseWord+" ";
                            }
                        }
                    }
                        output+=" score"+para1[i].score+"\n \n";
                        
                        //output+=para1[i].text;
                    }
                }
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            return output;
        }
        
        private void swap(int i, int i0,paragraphs [] para1) {
            int temp=para1[i].score;
            para1[i].score=para1[i0].score;
            para1[i0].score=temp;
            String ttemp=para1[i].text;
            para1[i].text=para1[i0].text;
            para1[i0].text=ttemp;
        }
    }
    
    
