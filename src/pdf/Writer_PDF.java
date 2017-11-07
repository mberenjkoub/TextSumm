/*
 * Writer_PDF.java
 *
 * Created on August 29, 2008, 6:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.faceless.pdf2.PDF;
import org.faceless.pdf2.PDF;
import org.faceless.pdf2.PDFAction;
import org.faceless.pdf2.PDFBookmark;
import org.faceless.pdf2.PDFFont;
import org.faceless.pdf2.PDFPage;
import java.util.*;
import java.io.*;
import java.awt.Color;
import org.faceless.pdf2.*;

/**
 *
 * @author mar
 */
public class Writer_PDF {
    
    private static String ds[];

    private static String NORMALFONT = "dist\\times.ttf";


    // The list of languages to use and their names. Note that Georgian (ISO code "ka") and
    // Thai (ISO code "th") are not included in this list, even though we have the translation, 
    // because we haven't got a font with the required glyphs
    //
    private static String[] langs = { "ar"};//, "ca", "da", "de", "el", "en", "eo", "es", "fi", "fr", "ga", "he", "hu", "it", "ja", /*"ka", */ "ko", "nl", "no-bok", "no-nyn", "oc", "pt-br", "pt-pt", "ro", "ru", "sl", "sv", /*"th", */ "yi", "zh-cn", "zh-tw" };
    private static String[] names = { "Arabic", "Catalan", "Danish", "German", "Greek", "English", "Esperanto", "Spanish", "Finnish", "French", "Irish Gaelic", "Hebrew", "Hungarian", "Italian", "Japanese", /*"Georgian", */ "Korean", "Dutch", "Norwegian (Bokmal)", "Norwegian (Nynorsk)", "Occitan", "Portuguese (Brazil)", "Portuguese (Portugal)", "Romanian", "Russian", "Slovakian", "Swedish", /*"Thai", */ "Yiddish", "Chinese (Simplified)", "Chinese (Traditional)" };

    private static PDFFont opentypefont;

    private static  int Length;

    private static org.faceless.pdf2.PDFFont builtinfont;
    
    /** Creates a new instance of Writer_PDF */
    public Writer_PDF() {
    }
    public void Writer_Doc_2_PDF(String[] text) throws IOException {
        Parser p=new Parser();
       /* String[] paragraph_Word = text.split( "\\n" ); // split on commas
        String str="";
        try{
            for (int i = 0; i<paragraph_Word.length; i++) {
                if(paragraph_Word[i]!=null){
                    //paragraph_Word[i]=paragraph_Word[i].trim();
                    String[] word_Of_Paragraph=paragraph_Word[i].split("\\s");
                    // System.out.println("para"+paragraph_Word[i]+"\n");
                    for (int j = 0; j < word_Of_Paragraph.length; j++) {
                        StringBuffer reverseWord=new StringBuffer(word_Of_Paragraph[j]).reverse();
                        if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
                            
                                str=str.concat(reverseWord.toString()).concat(" ");
                               
                               
                            
                            
                            // System.out.println("word"+word_Of_Paragraph[j]+"\t" );
                        }
                    }
                    str=str.concat("\n");
                    System.out.println("\n");
                }
                
            }
           p. writer(str);
        }catch(Exception e1) {
            System.out.println(e1.getMessage());
        }*/
        
         NORMALFONT = "dist\\times.ttf";
       
        init();
      //  Length=text.length();

        PDF pdf = new PDF();
        //addPage(pdf, "Intro", "C://resources/utf8/intro.txt", getFont("intro"), 16, Locale.ENGLISH,text);

        // Add the language pages
        for (int i=0;i<text.length;i++) {
            String file = text[i];
            Locale locale;
          
                locale = new Locale(langs[0]);
           
            addPage(pdf, names[i], file, getFont(langs[0]), 14, locale,text[i]);
        }

        pdf.setOption("pagelayout", "OneColumn");
        pdf.setOption("pagemode", "UseOutlines");
        pdf.setInfo("Subject", "Demonstration of Unicode functionality\nin the Big Faceless PDF Library");

        // Create the file
        OutputStream out = new FileOutputStream("Unicode.pdf");
        pdf.render(out);
        out.close();
    }
     public static void addPage(PDF pdf, String title, String file, PDFFont font, float size, Locale l,String text) throws IOException
    {
        // Create a new page, and add a bookmark to it
        PDFPage page = pdf.newPage(PDF.PAGESIZE_A4);
        pdf.getBookmarks().add(new PDFBookmark(title, PDFAction.goTo(page)));

        // Create the header text
        pdf.setLocale(Locale.ENGLISH);
        PDFStyle style = new PDFStyle();
        style.setFillColor(Color.black);
        style.setFont(new StandardFont(StandardFont.HELVETICAOBLIQUE), 18);
        page.setStyle(style);
        page.drawText(title, 50, page.getHeight()-50);

        // Add gratuitous hyperlink back to our website
        style.setFont(new StandardFont(StandardFont.HELVETICAOBLIQUE), 10);
        page.setStyle(style);
        page.beginText(50, 50, page.getWidth()-50, 70);
        page.drawText("Created with the ");
        page.beginTextLink(PDFAction.goToURL(new java.net.URL("http://bfo.co.uk/products/pdf")), PDFStyle.LINKSTYLE);
        page.drawText("Big Faceless PDF Library");
        page.endTextLink();
        page.endText(false);

        // Create the body of the text. Be sure to set the working locale of the PDF
        // to the correct locale for the language. This changes various things, most of
        // them fairly subtle changes to do with default text-direction, line wrapping
        // styles etc.
        //
        // After we set the locale, we create a brand new style so it will pick up any
        // text-direction changes implied by the locale. If we reused the same style we
        // defined above, all the text would be left-aligned (or whatever the default is
        // for Locale.ENGLISH), even hebrew and arabic!
        pdf.setLocale(l);

        // Create a new style, in the specified font
        style = new PDFStyle();
        style.setFillColor(Color.black);
        style.setFont(font, size);
        page.setStyle(style);

        // Display the text on the page. First jump through the hoops Java sets us to
        // load files in UTF-8
        page.beginText(50, 50, page.getWidth()-50, page.getHeight()-100);
        //BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String[] s=text.split("\\n");
       
        for (int j = 0; j <s.length; j++) {
           if(s[j]!=null)
              page.drawText(page.getStyle().getFont().requote(s[j], pdf.getLocale()) + "\n");
              //if(j%21==0&&j!=0){
                  
                  
              //}
        }
       // String t=text.substring(j);
      //  addPage(pdf, "", "", getFont("ar"), 14, l,t);
        //r.close();
        page.endText(false);
    }


    //----------------------- UTILITY METHODS FROM HERE ON ----------------------------


    /**
     * Get the font to be used for the specified language
     */
    private static PDFFont getFont(String lang) {
       
            return opentypefont;
        
    }

    /**
     * Initialize the fonts to be used
     */
    private static void init() throws IOException {
        // Load the Font to be used for most of the examples. If you get an error
        // here, you probably haven't changed the NORMALFONT String to point to
        // the TrueType font file you want to use (we don't supply one with this library).
        //
        InputStream in = new FileInputStream(NORMALFONT);
        opentypefont = new OpenTypeFont(in, 2);
        in.close();
        builtinfont = new StandardFont(StandardFont.TIMES);
    }


    public static void main(String[] args) throws IOException {
        DOCRead doc=new DOCRead();
        
        Parser p=new Parser();
        ds= doc.readDocFile("E://doc.doc");
        Writer_PDF wr=new Writer_PDF();
        wr.Writer_Doc_2_PDF(ds);
        //p.writer(ds[0]);
        
    }
    
}
