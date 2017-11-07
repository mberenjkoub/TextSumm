package pdf;
import java.io.IOException;

import Stemming.Stemmer;
import pdf.Parser;
import pdf.comparison;
import pdf.paragraphs;
import pdf.textword;
public class TotalDocumentFrequencySearch {
	/*
	 * absSearch.java
	 *
	 * Created on September 11, 2008, 3:12 PM
	 *
	 * To change this template, choose Tools | Template Manager
	 * and open the template in the editor.
	 */

	

	/**
	 * 
	 * @author mar
	 */
	


		private int[] score_Of_Para;

		private String[] paragraphs;

		private String s;

		private Parser p = new Parser();

		private int count;

		private String s1;

		/** Creates a new instance of Search */
		public TotalDocumentFrequencySearch() {

			s = "";

		}

		public boolean Search_Keywords(textword[] w, String word, int num_Of_Word) {
			// String[] words=keywords.split("\\s");
			int k = 0;
			word = word.trim();
			try {

				for (k = 0; k < num_Of_Word; k++) {
					w[k].word = w[k].word.trim();
					/*
					 * for (int i = 0; i < word.length(); i++) {
					 * System.out.println(word.codePointAt(i)+"i"+i); } for (int i =
					 * 0; i < w[k].word.length(); i++) {
					 * System.out.println(i+w[k].word.codePointAt(i)); }
					 */

					if (word.equalsIgnoreCase(w[k].word)
							|| Equal_Word(word, w[k].word)) {
						w[k].score++;
						System.out.println("bah");
						// w[k].setScore(score_Of_Word[k]);
						// p.writer(s1);
						return true;
					}
					/*
					 * if(!words[k].isEmpty()||!words[k].equals("")){
					 * if(words[k].equalsIgnoreCase(word)){ score_Of_Word[k]++;
					 * w[k].setScore(score_Of_Word[k]);
					 * 
					 * System.out.println("score"+score_Of_Word[k]);
					 * 
					 * return true; } }
					 */
					// }
					// s+="\n";
					// System.out.println("score"+score_Of_Para[num_Of_Para]);

				}

				/*
				 * try{ for (int i = 0; i<paragraphs.length; i++) {
				 * if(paragraphs[i]!=null){
				 * //paragraph_Word[i]=paragraph_Word[i].trim(); String[]
				 * word_Of_Paragraph=paragraphs[i].split("\\s"); //
				 * System.out.println("para"+paragraph_Word[i]+"\n"); for (int j =
				 * 0; j < word_Of_Paragraph.length; j++) {
				 * 
				 * if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
				 * if(word_Of_Paragraph[j].equalsIgnoreCase(words[i])==true) {
				 * score_Of_Para[k]++; }
				 *  // System.out.println("word"+word_Of_Paragraph[j]+"\t" ); } }
				 * System.out.println("score"+score_Of_Para[num_Of_Para]);
				 * 
				 * num_Of_Para++;
				 * 
				 * System.out.println("\n"); }
				 *  }
				 * 
				 * }catch(Exception e1) { System.out.println(e1.getMessage()); } }
				 * 
				 */
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			// score_Of_Word[k]++;
			/*
			 * if(k==1&&keywords.equals("")==true){ w[num_Of_Word]=new textword();
			 * w[num_Of_Para].setWord(word,1); } else { w[num_Of_Word]=new
			 * textword(); w[num_Of_Word].setWord(word,1); } num_Of_Word++;
			 */
			return false;
		}

		public void get_Score(textword[] w, String keywords, paragraphs[] p1,
				int c, int num_Of_Word, int num_Of_Para) throws IOException {
			// p.writer(s);
			String s1 = "";
			this.count = c;
			// for (int i = 0; i < num_Of_Word; i++) {

			// s1+="score "+w[i].word+" "+w[i].score+"\n";
			// }

			String[] para = keywords.split("\\n");
			try {
				for (int i = count; i < num_Of_Para; i++) {
					if (p1[i].equals("") == false && p1[i] != null) {
						s1 += p1[i] + "\n";
						String[] words = p1[i].text.split("\\s");

						for (int j = 0; j < words.length; j++) {
							if (!words[j].isEmpty() || !words[j].equals("")) {

								for (int k = 0; k < num_Of_Word; k++) {
									String r = words[j];
									if (words[j].equalsIgnoreCase(w[k].word)) {
										p1[i].score += w[k].score;
										s1 += "score " + w[k].word + "  "
												+ w[k].score + "\n";
									}
								}

							}
							// p1[i].setparag_Score(p1[i]);
							System.out.println("paragraph " + i + "scores "
									+ p1[i].score + "\n");
						}
					}
				}
				p.writer(s1);
			}

			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}

		public void get_Score_DOC(textword[] w, String[] keywords, paragraphs[] p1,
				int c, int num_Of_Word, int num_Of_Para) throws IOException {
			// p.writer(s);
			String s1 = "";
			this.count = c;
			int y=0;
		//	paragraphs[] p2=new paragraphs[num_Of_Para]; 
			//p2[y]=new paragraphs();
			int code=0;
		/*	for (int i = 0; i < num_Of_Para; i++) {
			//	char []chars=p1[i].text.toCharArray();
				//if(p1[i].text.charAt(p1[i].text.length()-1)=='.')
				for (int j = p1[i].text.length()-1; j >=0 ; j--) {
					
					//for (int j = word.length() - 1; j >= 0; j--) {
						code = p1[i].text.codePointAt(j);
					if(code==46){
						System.out.println("ssssssssssssss");
				
					
				p2[y].text+=p1[i].text;}
				else{
					p2[y].text+=p1[i].text;
					p2[++y]=new paragraphs();
				}
					
			}
			}*/
			try {
				// for (int i = count; i < num_Of_Para; i++) {
				for (int i = count; i <num_Of_Para; i++) {

					if (p1[i].text.equals("") == false && p1[i] != null
							&& keywords[i].equals("") == false) {
						s1 += p1[i].text + "\n";
						// String[] words=p1[i].keyWords.toString().split("\\s");
						String[] words = keywords[i].split("\\s");
						for (int j = 0; j < words.length; j++) {
							if (!words[j].isEmpty() || !words[j].equals("")) {
								String root = words[j];

								if (!root.isEmpty() || !root.equals("")) {
									for (int k = 0; k < num_Of_Word; k++) {
										String r = w[k].word;
										if (root.equalsIgnoreCase(w[k].word)) {
											p1[i].score += w[k].score;
											s1 += "score " + w[k].word + "  "
													+ w[k].score + "\n";
										}
									}
								}

							}
							// p1[i].setparag_Score(p1[i]);
							
						}
						System.out.println("paragraph " + i + "scores "
								+ p1[i].score + "\n");
					}
					
				}
				p.writer(s1);
			}

			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			// for (int i = 0; i < num_Of_Word; i++) {

			// s1+="score "+w[i].word+" "+w[i].score+"\n";
			// }
			
			
			/*
			Stemmer stm = new Stemmer();
			String[] para = keywords;
			int f = c;c++;
			try {
				// for (int i = count; i < num_Of_Para; i++) {
				for (int i = 0; i < para.length; i++) {

					if (p1[c].text.equals("") == false && p1[c] != null
							&& para[i].equals("") == false) {
						s1 += p1[c].text + "\n";
						// String[] words=p1[i].keyWords.toString().split("\\s");
						String[] words = para[i].split("\\s");
						for (int j = 0; j < words.length; j++) {
							if (!words[j].isEmpty() || !words[j].equals("")) {
								String root = words[j];

								if (!root.isEmpty() || !root.equals("")) {
									for (int k = 0; k < num_Of_Word; k++) {
										String r = w[k].word;
										if (root.equalsIgnoreCase(w[k].word)) {
											p1[c].score += w[k].score;
											s1 += "score " + w[k].word + "  "
													+ w[k].score + "\n";
										}
									}
								}

							}
							// p1[i].setparag_Score(p1[i]);
							
						}
						System.out.println("paragraph " + c + "scores "
								+ p1[c++].score + "\n");
					}
					
				}
				p.writer(s1);
			}

			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}*/

		}

		public String Selection_Sort(paragraphs[] p2, int num_Of_Para,int qcount) {
			int Biggest = 0;
			
			String output = "";
			paragraphs[] para1=new paragraphs[p2.length];
			para1=p2;
			try {

				for (int i = count; i < num_Of_Para - 1; i++) {
					if (para1[i] != null) {
						Biggest = i;
						for (int j = i + 1; j < num_Of_Para; j++) {

							if (para1[j] != null
									&& para1[j].score > para1[Biggest].score)
								Biggest = j;
						}
						// output+=para1[Biggest].text;
						swap(i, Biggest, para1);
					}
				}
				int treshhold=20+count;

				for (int i = count; i < treshhold; i++) {
					if (para1[i] != null&&para1[i].score!=0) {
						output += " score" + para1[i].score + "\n \n";

						output += para1[i].text;
					}

					/*
					 * String[] word_Of_Paragraph=para1[i].text.split("\\s"); //
					 * System.out.println("para"+paragraph_Word[i]+"\n"); for (int j =
					 * word_Of_Paragraph.length-1; j >=0; j--) {
					 * if(!word_Of_Paragraph[j].isEmpty()||!word_Of_Paragraph[j].equals("")){
					 * 
					 * StringBuffer reverseWord=new
					 * StringBuffer(word_Of_Paragraph[j]).reverse();
					 * output+=reverseWord+" "; } } output+="
					 * score"+para1[i].score+"\n \n";
					 * 
					 * //output+=para1[i].text; }
					 */

				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			return output;
		}

		private void swap(int i, int i0, paragraphs[] para1) {
			int temp = para1[i].score;
			para1[i].score = para1[i0].score;
			para1[i0].score = temp;
			String ttemp = para1[i].text;
			para1[i].text = para1[i0].text;
			para1[i0].text = ttemp;
		}

		public boolean Equal_Word(String word1, String word2) {
			boolean Flag = true;
			if (word1.length() != word2.length())
				return false;
			for (int i = 0; i < word1.length() && i < word2.length(); i++) {

				if (word1.codePointAt(i) == word2.codePointAt(i))
					Flag = true;
				else if ((word1.codePointAt(i) == 1610 && word2.codePointAt(i) == 1740)
						|| (word2.codePointAt(i) == 1610 && word1.codePointAt(i) == 1740)) {
					Flag = true;
				} else
					return false;
			}
			return Flag;
		}
	}



