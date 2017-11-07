/*
 * ReaderPDF.java
 *
 * Created on August 27, 2008, 1:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

import Abstract.absparser;
import java.awt.Color;
import java.awt.PageAttributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import org.faceless.pdf2.*;
import org.omg.CORBA_2_3.portable.InputStream;
/**
 *
 * @author mar
 */
public class ReaderPDF {
    
    private static String output;
    
    private static OpenTypeFont opentypefont;
    
    private static String NORMALFONT="times.ttf";
    
    private static StandardFont builtinfont;

	private static StringBuffer size;

	private static String[] matn;
    
    
    
    /** Creates a new instance of ReaderPDF */
    public static void Reader(String infile) throws IOException {
        String outfile;
        output="";
        
        // for (int i=0;i<args.length;i++) {
        // String infile = "E:\\documents\\48.pdf";
        OutputStreamWriter out = new OutputStreamWriter(System.out, "UTF-16");
        out.write("\uFFFE"); // Byte Order Mark, to tell text-editors this is UTF-8
        
        System.err.print("Reading \""+infile+"\" ");
        long time1 = System.currentTimeMillis();
        PDF pdf1 = new PDF(new PDFReader(new File(infile)));
        long time2 = System.currentTimeMillis();
        
        System.err.print(" "+(time2-time1)+"ms. Extracting ");
        extractText(pdf1, out);
        
        long time3 = System.currentTimeMillis();
        System.err.println(" "+(time3-time2)+"ms.");
        out.flush();
        //}
    }
    private static void extractText(PDF pdf, Writer out) throws IOException {
        PDFParser parser = new PDFParser(pdf);
        Parser p=new Parser();
        PDF pdff=new PDF();
        String input="";
        for (int i = 0; i < pdf.getNumberOfPages(); i++) {
            
            PageExtractor extractor = parser.getPageExtractor(i);
            input+=extractor.getTextAsStringBuffer().toString();
        }
        absparser abs=new absparser();
         String str=abs.parsing_Words("StopWordForPDFFiles.doc",input);
        
       // String str=p.parsing_Words(input);
       
       /*   size=new StringBuffer(str);
         size.capacity();
         int count_Of_Page=0;
         int index_Of_end;
         int index_Of_bigin=1,k=1;
         try {
        	 
        	 char[]c1=new char[10000];int j=0;
        	 char [] c=str.toCharArray();
        	 for (int i = 0; i < c.length; i++) {
        		 c1[j]=c[i];
     			if(i>0&&i==(c.length/(k*pdf.getNumberOfPages()))){
     			
     			
     			matn[count_Of_Page++].copyValueOf(c1);
     			//String s2=str.(index_Of_bigin,i);
     			k++;j=0;
     			c1=new char[10000];
     			//index_Of_bigin=i;}
     		}
		} }catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}*/
        
         
        /*
         *
         *
         *
         *
        String  str=p.parsing_Words(input);
         *
         *
         *
         */
         
        String [] s=str.split("\\n");
        
       // String str="";
         //  addPage(pdff,str,getFont("ar"));
        DOCWriter dc=new DOCWriter();
        dc.writer("summary.doc",str);String t="";int index=0;
        for (int j=0;j<pdf.getNumberOfPages();j++) {
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
                //str=  p.parsing_Words(extractor.getTextAsStringBuffer().toString());
                addPage(pdff,t,getFont("ar"));
                //output=output.concat(extractor.getTextAsStringBuffer().toString()).concat("\n");
                
                
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        OutputStream outp = new FileOutputStream("e://Unicode1.pdf");
        pdff.render(outp);
        outp.close();
        
    }
    public static void addPage(PDF pdf_final,String t, PDFFont font) throws IOException {
        // Create a new page, and add a bookmark to it
        font=opentypefont;
        //  pdf_final=new PDF();
        
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
        //page.drawText("Big Faceless PDF Library");
        page.endTextLink();
        page.endText(false);
        
        
        pdf_final.setLocale(l);
        
        // Create a new style, in the specified font
        style = new PDFStyle();
        style.setFillColor(Color.black);
        style.setFont(font, size);
        page.setStyle(style);
        
        // Display the text on the page. First jump through the hoops Java sets us to
        // load files in UTF-8
        page.beginText(50, 50, page.getWidth()-50, page.getHeight()-100);
        // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String s="";
        //while ((s=r.readLine())!=null) {
        
       System.out.println("page"+ page.drawText(page.getStyle().getFont().requote(t, pdf_final.getLocale()) + "\n"));
        //  }
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
        // Load the Font to be used for most of the examples. If you get an error
        // here, you probably haven't changed the NORMALFONT String to point to
        // the TrueType font file you want to use (we don't supply one with this library).
        //
        java.io.InputStream in = new FileInputStream(NORMALFONT);
        opentypefont = new OpenTypeFont(in, 2);
        in.close();
        builtinfont = new StandardFont(StandardFont.TIMES);
    }
    
    private  void write() {
        //OutputStream outp = new FileOutputStream("Unicode.pdf");
        // pdf.render(outp);
        // outp.close();
    }
}










