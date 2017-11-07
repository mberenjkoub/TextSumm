/*
 * DOCWriter.java
 *
 * Created on September 8, 2008, 11:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pdf;

/**
 *
 * @author mar
 */

import java.util.*;
import java.io.*;
import java.lang.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
public class DOCWriter {
    public void writer(String filename,String s)
    {
       try {
            
                  InputStream fis = new FileInputStream("OutputFormat.doc");
                  HWPFDocument doc = new HWPFDocument(fis);
                 // CharacterRun run = new CharacterRun();
                  //run.setBold(true);
                  //run.setItalic(true);
                  //run.setCapitalized(true);
                 
                  //String s=r.Reader("E://documents//d.pdf");
                  fis.close();
                  Range range = doc.getRange();
                  range.insertBefore(s);
                 
                  OutputStream out = new FileOutputStream(filename);
                  doc.write(out);

                  out.flush();
                  out.close();
      
      
            } catch (Exception ex) {
                  ex.printStackTrace();
            }
    }
}
