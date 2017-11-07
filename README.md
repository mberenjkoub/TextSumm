# TextSumm

Persian Text Summarizer is a software designed to summarize the persian texts. More specifically, the input of this software are persian articles in doc or pdf format. 
The output is the summary of that articles. The size of the summary can be adjusted by the user.
This software implemented in Java using My SQL. We also load a most popular persian dictionary in MySQL and create a simple WORDNet using in order to do stemming in our software.

## Description
This software has three features:
- Summarize the selected file.
- find the stem of a word.
- find the meaning of a word.
- Load and open the file in both PDF and Microsoft word format.

## Binding to Other libraries
We used 
- [faceless](http://big.faceless.org)
- [PDFBox](http://www.pdfbox.org) for reading pdf file.
- [Apache POI](https://poi.apache.org/) for loading Microsoft word files.

## Use-Cases
- Loading_File.
- Stemming.
- Summarizing.

## Further Reading
[A method for stemming and eliminating common words for Persian text summarization](http://ieeexplore.ieee.org/abstract/document/5313836/)

## install
In order to install this software, you need to 
- Install MySQL first. Configure MySQL with username = root, password = root and port = 3306.
- Install MySQL Administrator and run it.
- Restore the file named DehkhodaDatabase.sql. 
- Run setup.exe 
and you can use the software.

