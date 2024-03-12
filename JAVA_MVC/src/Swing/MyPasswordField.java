
package Swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

//Custom JPasswordField
public class MyPasswordField extends JPasswordField {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";

    public MyPasswordField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#536976"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(Color.decode("#D7DDE8"));
        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
        paintIcon(g);
        g2.dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
//            g.setColor(Color.decode("#536976"));
            g.setColor(getForeground());
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
            g.dispose();
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }
    

    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            //  suffix is right
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}

//package Swing;
//
//import java.awt.Color;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Insets;
//import java.awt.RenderingHints;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JPasswordField;
//
////Custom JPasswordField
//public class MyPasswordField extends JPasswordField {
//
//    private Icon prefixIcon;
//    private Icon suffixIcon;
//    private String hint = "";
//
//    public MyPasswordField() {
//        setOpaque(false);
//        setForeground(Color.decode("#536976"));
//        setFont(new java.awt.Font("sansserif", 0, 13));
//        setSelectionColor(new Color(75, 175, 152));
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(Color.decode("#D7DDE8"));
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
//        paintIcon(g2);
//        g2.dispose();
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        if (getPassword().length == 0 && !hasFocus()) {
//            Graphics2D g2 = (Graphics2D) g.create();
//            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            Insets ins = getInsets();
//            FontMetrics fm = g2.getFontMetrics();
//            g2.setColor(Color.decode("#536976"));
//            int x = ins.left;
//            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
//            g2.drawString(hint, x, y);
//            g2.dispose();
//        }
//    }
//
//    private void paintIcon(Graphics2D g2) {
//        if (prefixIcon != null) {
//            Image prefix = ((ImageIcon) prefixIcon).getImage();
//            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
//            g2.drawImage(prefix, 10, y, this);
//        }
//        if (suffixIcon != null) {
//            Image suffix = ((ImageIcon) suffixIcon).getImage();
//            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
//            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
//        }
//    }
//
//    public String getHint() {
//        return hint;
//    }
//
//    public void setHint(String hint) {
//        this.hint = hint;
//        repaint();
//    }
//
//    public Icon getPrefixIcon() {
//        return prefixIcon;
//    }
//
//    public void setPrefixIcon(Icon prefixIcon) {
//        this.prefixIcon = prefixIcon;
//        initBorder();
//        repaint();
//    }
//
//    public Icon getSuffixIcon() {
//        return suffixIcon;
//    }
//
//    public void setSuffixIcon(Icon suffixIcon) {
//        this.suffixIcon = suffixIcon;
//        initBorder();
//        repaint();
//    }
//
//    private void initBorder() {
//        int left = 15;
//        int right = 15;
//        if (prefixIcon != null) {
//            left = prefixIcon.getIconWidth() + 20;
//        }
//        if (suffixIcon != null) {
//            right = suffixIcon.getIconWidth() + 15;
//        }
//        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
//    }
//}
