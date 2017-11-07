package UserInterfaceDesign;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Frame1 extends JFrame {
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();

    public Frame1() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setBounds(new Rectangle(0, 2, 406, 300));
        this.getContentPane().add(jPanel1, null);
        jPanel1.add(jLabel1);
        jLabel1.setText("jLabel1");
    }
}
