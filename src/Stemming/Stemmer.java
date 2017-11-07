package Stemming;

import java.io.IOException;

import pdf.DOCRead;
import pdf.Parser;
import pdf.ReaderPDF;
import pdf.stop_Word_Reader;

public class Stemmer {

	private static final int FINAL = 100;

	private static final int START = 0;

	private static final int GETA_N = 1;/////an

	private static final int GETH = 2;/////h

	private static final int GETI_L = 3;/////i

	private static final int GETN_L = 4;/////n

	private static final int GETR = 5;/////r

	private static final int GETM = 6;/////m

	private static final int GETI_N = 7;////in

	private static final int GETT_L = 8;////t

	private static final int GETSH = 9;/////sh

	private static final int GETT_A = 10;////tan

	private static final int GETN_M = 11;/////man

	private static final int GETD = 12;/////d

	private static final int GETK = 13;//////k

	private static final int GETA_K = 14;/////ak

	private static final int GETA_H = 15;/////ah

	private static final int GETA_I = 16;///// ai

	private static final int GETA_R = 17;///// ar

	private static final int GETI_I = 18;/////ii

	private static final int GETI_M = 19;////im

	private static final int GETI_T = 20;////it

	private static final int GETN_H = 21;////ne

	private static final int GETD_H = 22;////de

	private static final int GETN_I = 23;////ni

	private static final int GETAN_H = 24;////ane

	private static final int GETO_N = 25;////oon

	private static final int GETA_M = 26;/////am

	private static final int GETMA_N = 27;////man
	
	private static final int GETSHA_N = 28;////shan

	private static final int GETCH_H = 29;////che
	
	private static final int GETRI_N = 30;////che
	
	private static final int GETPARANTESE = 31;////che
	
	private static final int GETQUTATION = 32;////che

	private int code;

	private int state;

	private static String[] paragraph_Word;



	public String Deletemarks(String word) {
		state = START;
		for (int j = word.length() - 1; j >= 0; j--) {
			code = word.codePointAt(j);
			if(code==8211)
				return "";
			else if (code==34) {
				//
				if (word.codePointAt(0)==34) {
					word=word.substring(1,j);
					return word;
				}
				word=word.substring(0,j);
			}
			// /cama
			else if (code == 1548)
				return Deletemarks(word.substring(0, j));

			// //.

			else if (code == 46)
				return Deletemarks(word.substring(0, j));
			
			////:
			else if(code==58)
				return Deletemarks(word.substring(0,j));
			
////		/
			else if(code==1616)
				return Deletemarks(word.substring(0,j));
			
			/////(
			else if(code==41)
			{
				state=GETPARANTESE;
				word=word.substring(0,j);
			}
			else if(code==40)
				return word.substring(1,word.length());
			
			
		}
		return word;
	}
	public String Find_Root(String word) {
		state = START;
		for (int j = word.length() - 1; j >= 0; j--) {
			code = word.codePointAt(j);
			
			///////First state
			if (j == word.length() - 1 && state == START) {

				// // ?//ii
				if (code == 65266 || code == 1610 || code == 1740) {
					System.out.println("i" + j);
					state = GETI_L;

				}

				// //?aa
				else if (code == 1575 || code == 65166) {
					state = GETA_I;
				}

				// /cama
				else if (code == 1548)
					return Find_Root(word.substring(0, j));

				// //.

				else if (code == 46)
					return Find_Root(word.substring(0, j));
				
				////:
				else if(code==58)
					return Find_Root(word.substring(0,j));
				
				/////(
				else if(code==41)
				{
					state=GETPARANTESE;
					word=word.substring(0,j);
				}
				
				else if(code==34){
					state=GETQUTATION;
					word=word.substring(0,j);
				}

				// /?n

				else if (code == 1606) {
					state = GETN_L;
					System.out.println("n" + j);
				}

				// //?r
				else if (code == 1585)
					state = GETR;

				// //?m
				else if (code == 1605)
					state = GETM;

				// //?t
				else if (code == 1578)
					state = GETT_L;

				// ///?sh
				else if (code == 1588)
					state = GETSH;

				// /// ?h
				else if (code == 1607)
					state = GETH;

				// ////?
				else if (code == 1583)
					state = GETD;

				else if (code == 1705)
					state = GETK;

			}

			// ///////////First Step

			else if (state == GETH) {

				// /////// ??neh (e.g kamineh)
				if (code == 1606) {
					state = GETN_H;
					System.out.println("h" + j);
				}

				// ///// ??deh  (e.g daneshkadeh)
				else if (code == 1583) {
					state = GETD_H;
				}

				// ////// ??ah  (e.g daneshgah)
				else if (code == 1607 || code == 1575) {
					state = GETA_H;
				}
				// //// che		(e.g baghche)
				else if (code == 1670) {
				return word.substring(0,j);
				}
					/////// 
					else
						state=START;
				}
				

				else if (state == GETSH) {
					
					// ////?? iash(ketabhaiash)
					if (code == 1740)
						state = GETI_M;

					else
						state = START;
				

				// state=START;
				// else state=0;
			} 
				
				
			
				else if (state == GETN_L) {
					// ///// ??in
					if (code == 1740||code==1610)
						state = GETI_N;

					// /////??an
					else if (code == 1575)
						state = GETA_N;

					else if (code == 1608)
						state = GETO_N;

					else
						state = START;
				}
			
				else if (state == GETD) {

					// ////??mand (e.g servatmand)
					if (code == 1606)
						state = GETN_M;

					else
						state = START;
				}
				else if(state==GETRI_N)
				{
//					 //// ???? ??tar (e.g behtar)
					if (code == 1578)
						return word.substring(0, j);
					
				}
			
				else if (state == GETR) {
					

					// // ??at (e.g etelaat)
					if (code == 1575)
						state = GETA_R;

					// //// ??var  (e.g honarvar)
					else if (code == 1608)
						return word.substring(0, j);

					else
						state = START;
				} 
				
				else if (state == GETI_L) {
					// //// ??
					if (code == 1575 || code == 65166 || code == 65153)
						state = GETA_I;

					else if (code == 1606)
						state = GETN_I;

					// /// ??
					else if (code == 65268 || code == 1610)
						state = GETI_I;
					/////chi
					else if (code == 1670) {
						return word.substring(0, j);
					}

					// // ?? ??
					else if (code == 1574 || code == 1711)
						return word;
					else
						return word.substring(0, j + 1);
				}
			
				else if(state==GETCH_H)
				{
					return word.substring(0,j);
				}

				
				else if (state == GETA_I) {
				// /// ??? ?? ????
				if (code == 65259 || code == 1607 || code == 65257
						|| code == 65260) {
					System.out.println("a" + j);
					return Find_Root(word.substring(0, j));
				} else
					return word;
			}

			else if (state == GETA_R) {
				// // ??? ar  (e.g namakzar)
				if (code == 1587)
					return word.substring(0, j);

				// /// ???
				else if (code == 1586)
					return word.substring(0, j);

				else
					// return word.substring(0,j+1);
					state = START;
			}

			else if (state == GETI_I) {
				// /////???
				if (code == 1575 || code == 65166 || code == 65153)
					state = GETA_I;
				else
					state = START;
			}

			else if (state == GETI_M) {
				// ////// ??? ???
				if (code == 1575)
					state = GETA_I;

				else
					state = START;

			}
			
			else if(state==GETPARANTESE)
			{
				if(code==40)
					return Find_Root(word.substring(1,word.length()));
			}
			
			else if(state==GETQUTATION)
			{
				if(code==34)
					return Find_Root(word.substring(1,word.length()));
			}

			else if (state == GETI_T) {
				// ////???
				if (code == 1575)
					state = GETA_I;
				else
					// // ??
					return word.substring(0, j + 1);

			} else if (state == GETA_N) {

				// /// ???man
				if (code == 1605)
					state = GETMA_N;

				// ////???tan
				else if (code == 1578)
					state = GETT_A;

				// //// ???shan
				else if (code == 1588)
					state = GETSHA_N;

				// // ???dan
				else if (code == 1583)
					return word.substring(0, j);

				// // ???san
				else if (code == 1587)
					return word.substring(0, j);

				// ///// gan
				else if (code == 1711)
					return word.substring(0, j);

				else
					// return word.substring(0,j+1);
					state = START;
			}
			
			else if(state==GETMA_N)
			{
				if(code==1740||code==1610)
					state=GETI_M;
				else
					return word.substring(0,j+1);
			}
			
			else if(state==GETSHA_N)
			{
				if(code==1740||code==1610)
					state=GETI_M;
				else if(word.substring(0,j).length()==1)
					return word;
				else
					return word.substring(0,j+1);
			}

			else if (state == GETA_H) {
				// /// ???
				if (code == 1711)
					return word.substring(0, j);

				else
					state = START;
			}

			
			else if (state == GETO_N) {
				if (code == 1711)
					return word.substring(0, j);
				else
					state = START;
			}

			else if (state == GETN_M) {

				// ///???
				if (code == 1605)

					return word.substring(0, j);

				else
					state = START;
			}

			else if (state == GETN_H) {
				// //???ine
				if (code == 1740)
					return word.substring(0, j);

				// ///ane
				else if (code == 1575)
					state = GETAN_H;
				else
					state = START;
			}

			else if (state == GETAN_H) {
				// ///gane
				if (code == 1711)
					return word.substring(0, j);

				else
					return word.substring(0, j + 1);
			} 

			else if (state == GETD_H) {
				// ////???
				if (code == 1705)
					return word.substring(0, j);
				else
					state = START;
			} 

			else if (state == GETN_I) {
				if (code == 1575)
					return word.substring(0, j);
				else
					return word.substring(0, j + 2);
			}

			else if (state == GETI_N) {
				// /// ???
				if (code == 1585)
					state = GETRI_N;

				else if (code == 65259 || code == 1607 || code == 65257
						|| code == 65260)
					state = GETH;
				// /// ???
				else if (code == 1711)
					return word.substring(0, j);
				else
					state = START;
			}

			else if (state == GETM) {
				// /////??im
				if (code == 1740 || code == 1610)
					state = GETI_M;

				else if (code == 1575)
					state = GETA_M;

				else
					state = START;
			}

			else if (state == GETA_M) {
				if (code == 1601)
					return word.substring(0, j);
				else
					state = START;

			}

			else if (state == GETK) {
				// ///??ak
				if (code == 1575)
					state = GETA_K;
				else
					state = START;
			}

			else if (state == GETA_K) {
				// /// ???
				if (code == 1606)
					return word.substring(0, j);
				else
					state = START;
			}

			else if (state == GETT_L) {
				// ////// ??
				if (code == 1575)
					return word.substring(0, j);

				// ///??
				else if (code == 1610 || code == 1740)
					state = GETI_T;

				else
					state = START;
			} else if (state == GETT_A) {
				// ////????
				if (code == 1740)
					state = GETI_M;
				// ////????
				else if (code == 1587)
					return word.substring(0, j);

				// ////???
				else
					return word.substring(0, j+1);
			}


			else if (state == FINAL)
				return word.substring(0, j);

			// else state=0;
			// }
		}
		return word;
	}


	
}