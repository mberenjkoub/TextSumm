/*
 * stop_Word_Reader.java
 *
 * Created on August 29, 2008, 11:38 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 *
 * @author mar
 */
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.faceless.pdf2.PDF;
import org.faceless.pdf2.PDFParser;
import org.faceless.pdf2.PDFReader;
import org.faceless.pdf2.PageExtractor;

public class stop_Word_Reader {

    private String output;
    
    /** Creates a new instance of stop_Word_Reader */
    public stop_Word_Reader() {
    }
    /*
 * ReaderPDF.java
 *
 * Created on August 27, 2008, 1:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */




/**
 *
 * @author mar
 */
    public String Reader() throws IOException {
        String outfile;
        // for (int i=0;i<args.length;i++) {
        String infile = "StopwordPDFfile.pdf";
        OutputStreamWriter out = new OutputStreamWriter(System.out, "UTF-16");
        out.write("\uEFFF"); // Byte Order Mark, to tell text-editors this is UTF-8
        
        System.err.print("Reading \""+infile+"\" ");
        long time1 = System.currentTimeMillis();
        PDF pdf = new PDF(new PDFReader(new File(infile)));
        long time2 = System.currentTimeMillis();
        
        System.err.print(" "+(time2-time1)+"ms. Extracting ");
       PDFParser parser = new PDFParser(pdf);
        for (int j=0;j<pdf.getNumberOfPages();j++) {
            System.err.print(".");
            PageExtractor extractor = parser.getPageExtractor(j);
            
            try{
                                          
              output=extractor.getTextAsStringBuffer().toString();
                           
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }  
        long time3 = System.currentTimeMillis();
        System.err.println(" "+(time3-time2)+"ms.");
        out.flush();
         return output;
        //}
    }
    public String Reader_File(String infile) throws IOException {
        String outfile;
        // for (int i=0;i<args.length;i++) {
        //String infile = "E:\\documents\\Summary.pdf";
        OutputStreamWriter out = new OutputStreamWriter(System.out, "UTF-16");
        out.write("\uEFFF"); // Byte Order Mark, to tell text-editors this is UTF-8
        
        System.err.print("Reading \""+infile+"\" ");
        long time1 = System.currentTimeMillis();
        PDF pdf = new PDF(new PDFReader(new File(infile)));
        long time2 = System.currentTimeMillis();
        
        System.err.print(" "+(time2-time1)+"ms. Extracting ");
       PDFParser parser = new PDFParser(pdf);
        for (int j=0;j<pdf.getNumberOfPages();j++) {
            System.err.print(".");
            PageExtractor extractor = parser.getPageExtractor(j);
            
            try{
                                          
              output+=extractor.getTextAsStringBuffer().toString();
                           
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }  
        long time3 = System.currentTimeMillis();
        System.err.println(" "+(time3-time2)+"ms.");
        out.flush();
         return output;
        //}
    }
    
}







    

