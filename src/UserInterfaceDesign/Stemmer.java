/*
 * Stemmer.java
 *
 * Created on November 13, 2008, 7:51 PM
 */

package UserInterfaceDesign;

import db.mydb;
import javax.swing.JTextField;

/**
 *
 * @author  mar
 */
public class Stemmer extends javax.swing.JFrame {
    
    /** Creates new form Stemmer */
    public Stemmer() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\mar\\Final\\pic\\pic1-1.jpg"));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\mar\\Final\\pic\\pic2-1.jpg"));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        jLabel3.setText("\u06a9\u0644\u0645\u0647 \u0645\u0648\u0631\u062f \u0646\u0638\u0631 \u0631\u0627 \u0648\u0627\u0631\u062f \u06a9\u0646\u06cc\u062f");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 160, -1));

        jButton2.setText("\u0631\u06cc\u0634\u0647 \u06cc\u0627\u0628");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 130, -1));

        jButton4.setText("\u062e\u0627\u0646\u0647");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 60, -1));

        jLabel4.setFont(new java.awt.Font("Zar", 0, 14));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 240, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
          Home h=new Home();
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        mydb db=new mydb();
        db.Connect();
        
        String sword=jTextField1.getText();
        String word=sword;
        for (int i = 0; i < word.length(); i++) {
			data=db.Select("select coordinateterm from words where word='"+sword+"'");
			if(data.length==0){
				sword=word.substring(0,word.length()-i);
                        }
			else
			{
				
				relateddata=db.Select("select relatedword from words,relation_words where word='"+sword+"' and words.word_id=relation_words.word_id");
				if(relateddata.length==0){
					jLabel4.setText(sword);
                                break;
                                }
				else {
					jLabel4.setText(relateddata[0][0]);
                                        break;
                                }
			}
                                
    }//GEN-LAST:event_jButton2ActionPerformed
        }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stemmer().setVisible(true);
            }
        });
    }
/*
    private void FindFrequency() {
        try {
			for (int i = 0; i < paragraph_Word.length; i++) {
				
					
				keyywords[i]=new String();
				keyywords[i]="";
				//if (paragraph_Word[i] != null && !paragraph_Word[i].isEmpty()
						//|| !paragraph_Word[i].equals("")) {// &&paragraph_Word[i].endsWith(".")){

					// paragraph_Word[i]=paragraph_Word[i].trim();

					count_Of_Word = 0;
					num_Of_KeyWords=0;
					
					String[] word_Of_Paragraph = paragraph_Word[i].split("\\s");

					p1[count_Of_Para].setparag_Word(paragraph_Word[i]);
					p1[count_Of_Para].keyWords=keyWords;
					p1[++count_Of_Para] = new paragraphs();
					p1[count_Of_Para].setparag_Word("");

					// p1[count].text+=paragraph_Word[i];

					// System.out.println("para"+paragraph_Word[i]+"\n");
					for (int j = 0; j < word_Of_Paragraph.length; j++) {
						StringBuffer reverseWord = new StringBuffer(
								word_Of_Paragraph[j]).reverse();
						if (!word_Of_Paragraph[j].isEmpty()
								|| !word_Of_Paragraph[j].equals("")) {
							count_Of_Word++;
							for (int f = 0; f < abstract_Word.length; f++) {

								if ((word_Of_Paragraph[j]
										.equalsIgnoreCase(abstract_Word[f]) == true)
										|| abstract_Word[f]
												.equalsIgnoreCase(reverseWord
														.toString()) == true) {
									p1[count_Of_Para]
											.setparag_Word(paragraph_Word[i]);
									p1[count_Of_Para].keyWords=keyWords;
									p1[++count_Of_Para] = new paragraphs();
									p1[count_Of_Para].setparag_Word("");

									foundAbstract(i, count_Of_Para);
							i = index_Of_Bigin;
							
									FINISHED = true;
									word_Of_Paragraph = paragraph_Word[index_Of_Bigin]
											.split("\\s");j=0;
									// return abskeywords;
									break;
								}
							}

							if (FINISHED
									&& cmp
											.Is_Stop_Word_WORD(word_Of_Paragraph[j]) == false)// reverseWord.toString())==false)
							{
								
									// parakey.append(word_Of_Paragraph[j]).append('
									// ');

									// str=str.concat(reverseWord.toString()).concat("
									// ");
									String root = stm.Find_Root(stm.Deletemarks(word_Of_Paragraph[j]));
									//String root=smp.dictionaryChecker(word_Of_Paragraph[j]);

									if ((!root.isEmpty()|| !root.equals("")	)&& s.Search_Keywords(words,root,
													count_Of_absWord) == true) {
										
										keyWords.append(root)
												.append(' ');
										
										keyywords[i]+=root+' ';

									
								}
								// Search(word_Of_Paragraph[j],paragraph_Word);

							}

							// System.out.println("word"+word_Of_Paragraph[j]+"\t"
							// );

						}

					}
					// str+=abstractKeywords.toString();
					str = str.concat("\n");
					keyWords.append("\n");
					parakey.append("\n");
					System.out.println("\n");

				}

		//	}
			// u.Writer(str);

			// writer(str);
			/*
			 * String [] w=keyWords.toString().split("\\s"); for (int i = 0; i <
			 * w.length; i++) { if(!w[i].equals("\n")&&w[i]!=null) s1+="score
			 * "+w[i]+" "+score_Of_Word[i]+"\n"; } writer(s1);
			 *//*
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		// matn.append( paragraph_Word[paragraph_Word.length - 1]);
		// p1[count_Of_Para].setparag_Word(matn.toString());
    }*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private String[][] data;

    private String[][] relateddata;
    
}
