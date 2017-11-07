/*
 * DOCRead.java
 *
 * Created on September 8, 2008, 10:11 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Abstract;

/**
 *
 * @author mar
 */
/*
 * DOCRead.java
 *
 * Created on August 23, 2008, 7:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



/**
 *
 * @author mar
 */
import java.io.*;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DOCRead1 {

public void readDocFile() {
    
   
File docFile = null;
File docFile1 = null;
WordExtractor docExtractor = null ;
WordExtractor docExtractor1 = null ;
WordExtractor exprExtractor = null ;
WordExtractor exprExtractor1 = null ;

try {
    
docFile = new File("E:\\word.doc");
docFile1 = new File("E:\\stop-word.doc");
//A FileInputStream obtains input bytes from a file. 
FileInputStream fis=new FileInputStream(docFile.getAbsolutePath());
FileInputStream fis1=new FileInputStream(docFile1.getAbsolutePath());

//A HWPFDocument used to read document file from FileInputStream
HWPFDocument doc=new HWPFDocument(fis);

docExtractor = new WordExtractor(doc);


HWPFDocument doc1=new HWPFDocument(fis1);

docExtractor1 = new WordExtractor(doc1);
}
catch(Exception exep)
{
System.out.println(exep.getMessage());
}

//This Array stores each line from the document file.
String [] docArray = docExtractor.getParagraphText();
String [] stobword = docExtractor1.getParagraphText();
char g;String h;

int ak[];
int temp;
String temps;
ak = new int[100]; 
String[] doca = new String[1000];
int ui=0;
int count[];     
count = new int[100];  
String r[];int yah=0;
int d=0;int max=0;int maxind=0;
String[] p = new String[100];String[] f = new String[100];String[] st = new String[100];
String[] keyword = new String[100];
String[] keywordmain = new String[100];
String pooch="";
String sa="Œ·«’Â";String mo="„ﬁœ„Â";char charArray3[] =sa.toCharArray();
for(ui=0;ui<1000;ui++){
    doca[ui]="";
}ui=0;
 for(int ll=0;ll<stobword.length;ll++)
     {
    st = stobword[ll].split("\\s");}

for(int i=0;i<docArray.length;i++)

{
p = docArray[i].split("\\s");
if(p[0].equals(sa)){
for(int t=i+1;t<docArray.length;t++) { System.out.println("docarray"+docArray[t]);
keyword = docArray[t].split("\\s");
 
for(int j=0;j<keyword.length;j++){yah=0;
    for(int jk=0;jk<st.length;jk++){ if(keyword[j].equals(st[jk])){yah=1;}}//for jk
   
    if(yah!=1){System.out.println("FALSE");
        if(keyword[0].equals(mo)){int y=t+1;j=keyword.length;t=docArray.length;i=docArray.length;}
        else if(!keyword[0].equals(mo)){d++;keywordmain[d]=keyword[j];System.out.println("keyword"+ j +" : " + keyword[j]);}
    }// if(!st[jk].equals(keyword[j]))

}//for key word
}//for t

}//if(p[0].equals(sa))


}//for i
////

for(int i=0;i<docArray.length;i++)

{
f = docArray[i].split("\\s");
if(f[0].equals(mo)){
    for(int t=i+1;t<docArray.length;t++) { 
    f = docArray[t].split("\\s");
for(int j=0;j<f.length;j++){
    for(int o=0;o<d;o++){      
  if(f[j].equals(keywordmain[o])){
     
       count[t]++;if(count[t]>max){max=count[t];maxind=t;}
   ak[ui]=count[t];
 }//iff[j].equals(keywordmain[o]
    }//foro
  }//forj
    
    doca[ui]=docArray[t];ui++;
    
    }//fort
}//if(f[0].equals(mo)
}//fori
///////////////
int j;ui=1;int yy=0;
while(doca[ui]!=""){

temp=ak[ui];
temps=doca[ui];
j=ui-1;
while(j>0&&temp<ak[j])
{
    ak[j+1]=ak[j];
    doca[j+1]=doca[j];
    j=j-1;
}
ak[j+1]=temp;yy++;
doca[j+1]=temps;
//if( ak[ui] > ak[ui+1] ) // out of order?
//{ temp = ak[ui];
 //temps=doca[ui];
//ak[ui] = ak[ui+1];
//doca[ui] = doca[ui+1];
//ak[ui+1] = temp;
//doca[ui+1] = temps;
//}
//else{}
ui=ui+1;
}


    try{
FileWriter f1=new FileWriter("E:\\raz2.doc");
while(yy>=0){
f1.write(doca[yy]);yy--;}
    f1.close();}
catch(Exception exep)
{
System.out.println(exep.getMessage());
}




//ui=0;
//while(doca[ui]!=null) {System.out.println("dooooc");// inner loop (forward)
//if( ak[ui] > ak[ui+1] ) // out of order?
//{ temp = ak[ui];
//ak[ui] = ak[ui+1];
//ak[ui+1] = temp;
//ui++;}}ui=0;
//while(doca[ui]!=null) {
 //System.out.println("doc"+ ui +"count"+ ak[ui]+doca[ui]);}
///////////////
}//readSystem.out.println("doc"+ ui +"count"+ ak[ui]+doca[ui]);}DocFile



public static void main(String[] args) {
DOCRead1 reader = new DOCRead1();
reader.readDocFile();

}
}//DOCRead
