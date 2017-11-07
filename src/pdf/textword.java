/*
 * word.java
 *
 * Created on September 3, 2008, 11:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 *
 * @author mar
 */
public class textword {
    public  String word;
    public int score;
    
    /** Creates a new instance of word */
    public textword() {
        this.word="";
        this.score=0;
    }
    public void setWord(String word,int score) {
        this.word=word;
        this.score=score;
    }
     public void setScore(int score) {
        this.word=word;
        this.score=score;
    }
    
    
}
