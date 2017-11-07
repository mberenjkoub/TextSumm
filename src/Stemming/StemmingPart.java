package Stemming;

import db.mydb;

public class StemmingPart {

	private String[][] data;
	private String[][] relateddata;
	private mydb db;
	private Stemmer s;
	/**
	 * @param args
	 */
	public  StemmingPart() {
		 db=new mydb();
		db.Connect();
		 s=new Stemmer();
		
	}
	public String dictionaryChecker(String word) {
		try{
		
		String sword=word;
		
		sword=s.Deletemarks(word);
		//for (int i = 0; i < word.length(); i++) {
			data=db.Select("select * from words where word='"+sword+"'");
			if(data.length==0)
				sword=word.substring(0,word.length()-1);
			else
			{
				
				relateddata=db.Select("select relatedword from words,relation_words where word='"+sword+"' and words.word_id=relation_words.word_id");
				if(relateddata.length==0)
					
					return sword;
				else {
					return relateddata[0][0];
			}
					
			//}
				
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		
			
		return word;
		
	}
}
