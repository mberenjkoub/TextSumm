/*
 * paragraphs.java
 *
 * Created on September 4, 2008, 3:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 *
 * @author mar
 */
public class paragraphs {
    
    private textword[] w;
    
   public int score;
    
   public String text;
   
   public StringBuffer keyWords;
    
    
    /** Creates a new instance of paragraphs */
    public paragraphs() {
    	keyWords=new StringBuffer("");
        
    }
    public void setparag_Word(String word) {
        text=word;
        
    }
    
    public void setparag_Score(int score) {
        this.score=score;
        
    }
    public boolean  Split(String text)
    {
    	int x=0;
    	text=text.trim();
        char[] c=text.toCharArray();
         for (int i = 0; i < c.length; i++) {
        	 if(text.startsWith("."))
        	      
             if(c[i]=='.'){ System.out.println("hooooooooooooooooooooooooooooooooooooraaaaaaaaaaa");
             return true;
             }
          
             
             
       
        }
        
        /*for (int i = 0; i < c.length; i++) {
            if(c[i]==' ')
                System.out.println(i+"space\n");
            else if(c[i]=='\u0009')
                System.out.println(i+"tab\n");
             else if(c[i]=='\n')
                System.out.println(i+"enter\n");
             else if(c[i]=='.')
                System.out.println(i+"dot\n");
        }*/
        return false;
    }
   
    
}
