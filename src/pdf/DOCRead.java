/*
 * DOCRead.java
 *
 * Created on August 23, 2008, 7:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 * 
 * @author mar
 */
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.faceless.pdf2.OpenTypeFont;
import org.faceless.pdf2.PDF;
import org.faceless.pdf2.PDFAction;
import org.faceless.pdf2.PDFBookmark;
import org.faceless.pdf2.PDFFont;
import org.faceless.pdf2.PDFPage;
import org.faceless.pdf2.PDFParser;
import org.faceless.pdf2.PDFStyle;
import org.faceless.pdf2.PageExtractor;
import org.faceless.pdf2.StandardFont;
import Abstract.absparser;
import java.awt.PageAttributes;
import java.io.Writer;
import org.faceless.pdf2.*;
import org.omg.CORBA_2_3.portable.InputStream;

import Abstract.absDOCParser;

public class DOCRead {
    
    private String output;

   private static OpenTypeFont opentypefont;
    
    private static String NORMALFONT="dist\\times.ttf";
    private static StandardFont builtinfont;

    public static String[] text;






	public String[] readDocFile(String Address)throws IOException {
        File docFile = null;
        WordExtractor docExtractor = null ;
        WordExtractor exprExtractor = null ;
        HWPFDocument doc=null;
       
        try {
            docFile = new File(Address);
// A FileInputStream obtains input bytes from a file.
            FileInputStream fis=new FileInputStream(docFile.getAbsolutePath());
            
// A HWPFDocument used to read document file from FileInputStream
            doc=new HWPFDocument(fis);
            
            docExtractor = new WordExtractor(doc);
        } catch(Exception exep) {
            System.out.println(exep.getMessage());
        }
        
// This Array stores each line from the document file.
        String [] docArray = docExtractor.getParagraphText();
        extractor e=new extractor();
        OutputStreamWriter out = new OutputStreamWriter(System.out, "UTF-16");
        out.write("\uEFFF"); // Byte Order Mark, to tell text-editors this is
								// UTF-8
        String s="";
        // out.write(docArray[0]);
        // doc.write(out);
       
        
        
    
        return docArray;
    }
    public void Reader1(String filename) throws IOException {
        File docFile = null;
        WordExtractor docExtractor = null ;
        WordExtractor exprExtractor = null ;
        try {
            docFile = new File(filename);
            // A FileInputStream obtains input bytes from a file.
            FileInputStream fis=new FileInputStream(docFile.getAbsolutePath());
            
            // A HWPFDocument used to read document file from FileInputStream
            HWPFDocument doc=new HWPFDocument(fis);
            
            docExtractor = new WordExtractor(doc);
        } catch(Exception exep) {
            System.out.println(exep.getMessage());
        }
        text=docExtractor.getParagraphText();
       // absDOCParser abs=new absDOCParser();
       // output=abs.parsing_Words(text);
        
         
        
       // TotalDocumentFrequencyParser p=new TotalDocumentFrequencyParser();
       // output=p.parsing_Words(text);
        
        
        // return text;
    }
    
    public void Write_toPDF() throws IOException {
    	PDF pdf=new PDF();
    	PDFParser parser = new PDFParser(pdf);
    	String t="";int index=0;
    	String []s=output.split("\\n");
    	  for (int j=0;j<10;j++) {
          	t="";
          	for (int i = index; i < s.length; i++) {
          	
      			if(i!=(j+1)*(s.length/(pdf.getNumberOfPages())))
      			
      				 t+=s[i]+"\n";
      				 
      			else
      			{
      				index=i;
      				
      				break;
      			}
      		}
          	
              System.err.print(".");
              PageExtractor extractor = parser.getPageExtractor(j);
             
              try{
                  init();
                  // str=
					// p.parsing_Words(extractor.getTextAsStringBuffer().toString());
                  addPage(pdf,t,getFont("ar"));
                  // output=output.concat(extractor.getTextAsStringBuffer().toString()).concat("\n");
                  
                  
              } catch(Exception e) {
                  System.out.println(e.getMessage());
              }
          }
          OutputStream outp = new FileOutputStream("Summary.pdf");
          pdf.render(outp);
          outp.close();
          
      }
      public static void addPage(PDF pdf_final,String t, PDFFont font) throws IOException {
          // Create a new page, and add a bookmark to it
          font=opentypefont;
          // pdf_final=new PDF();
          
          float size=14;
          String title="";
          PDFPage page = pdf_final.newPage(PDF.PAGESIZE_A4);
          
          Locale l = new Locale("ar");
          pdf_final.getBookmarks().add(new PDFBookmark(title, PDFAction.goTo(page)));
          
          // Create the header text
          pdf_final.setLocale(Locale.ENGLISH);
          PDFStyle style = new PDFStyle();
          style.setFillColor(Color.black);
          style.setFont(new StandardFont(StandardFont.HELVETICAOBLIQUE), 18);
          page.setStyle(style);
          page.drawText(title, 50, page.getHeight()-50);
          
          // Add gratuitous hyperlink back to our website
          style.setFont(new StandardFont(StandardFont.HELVETICAOBLIQUE), 10);
          page.setStyle(style);
          page.beginText(50, 50, page.getWidth()-50, 70);
         // page.drawText("Created with the ");
          page.beginTextLink(PDFAction.goToURL(new java.net.URL("http://bfo.co.uk/products/pdf")), PDFStyle.LINKSTYLE);
          // page.drawText("Big Faceless PDF Library");
          page.endTextLink();
          page.endText(false);
          
          
          pdf_final.setLocale(l);
          
          // Create a new style, in the specified font
          style = new PDFStyle();
          style.setFillColor(Color.black);
          style.setFont(font, size);
          page.setStyle(style);
          
          // Display the text on the page. First jump through the hoops Java
			// sets us to
          // load files in UTF-8
          page.beginText(50, 50, page.getWidth()-50, page.getHeight()-100);
          // BufferedReader r = new BufferedReader(new InputStreamReader(new
			// FileInputStream(file), "UTF-8"));
          String s="";
          // while ((s=r.readLine())!=null) {
          
         System.out.println("page"+ page.drawText(page.getStyle().getFont().requote(t, pdf_final.getLocale()) + "\n"));
          // }
          // r.close();
         System.out.println( page.endText(false));
          
      }
      
      
      private static PDFFont getFont(String lang) {
          
          
          return opentypefont;
          
      }
      
      /**
		 * Initialize the fonts to be used
		 */
      private static void init() throws IOException {
          // Load the Font to be used for most of the examples. If you get an
			// error
          // here, you probably haven't changed the NORMALFONT String to point
			// to
          // the TrueType font file you want to use (we don't supply one with
			// this library).
          //
          java.io.InputStream in = new FileInputStream(NORMALFONT);
          opentypefont = new OpenTypeFont(in, 2);
          in.close();
          builtinfont = new StandardFont(StandardFont.TIMES);
      }
      
      private  void write() {
          // OutputStream outp = new FileOutputStream("Unicode.pdf");
          // pdf.render(outp);
          // outp.close();
      }
  








		
	
    public static void main(String[] args) {
        DOCRead reader = new DOCRead();
        try{
            reader.readDocFile("StopWordForPDFFiles.doc");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
