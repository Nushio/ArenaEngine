package net.k3rnel.arena.tcg.main;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Launcher extends JDialog {

    private static final long serialVersionUID = 1L;

    private JCheckBox jCheckBox0;

    private JButton jButton0;

    private JRadioButton jRadioButton0;

    private JCheckBox jCheckBox1;

    private JRadioButton jRadioButton1;

    private ButtonGroup buttonGroup1;

    private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    public Launcher() {
        initComponents();
    }

    public Launcher(Frame parent) {
        super(parent);
        initComponents();
    }

    public Launcher(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Launcher(Frame parent, String title) {
        super(parent, title);
        initComponents();
    }

    public Launcher(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initComponents();
    }

    public Launcher(Frame parent, String title, boolean modal, GraphicsConfiguration arg) {
        super(parent, title, modal, arg);
        initComponents();
    }

    public Launcher(Dialog parent) {
        super(parent);
        initComponents();
    }

    public Launcher(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Launcher(Dialog parent, String title) {
        super(parent, title);
        initComponents();
    }

    public Launcher(Dialog parent, String title, boolean modal) {
        super(parent, title, modal);
        initComponents();
    }

    public Launcher(Dialog parent, String title, boolean modal, GraphicsConfiguration arg) {
        super(parent, title, modal, arg);
        initComponents();
    }

    public Launcher(Window parent) {
        super(parent);
        initComponents();
    }

    public Launcher(Window parent, ModalityType modalityType) {
        super(parent, modalityType);
        initComponents();
    }

    public Launcher(Window parent, String title) {
        super(parent, title);
        initComponents();
    }

    public Launcher(Window parent, String title, ModalityType modalityType) {
        super(parent, title, modalityType);
        initComponents();
    }

    public Launcher(Window parent, String title, ModalityType modalityType,
            GraphicsConfiguration arg) {
        super(parent, title, modalityType, arg);
        initComponents();
    }

    private void initComponents() {
    	setFont(new Font("Dialog", Font.PLAIN, 12));
    	setBackground(new Color(223, 223, 223));
    	setForeground(Color.black);
    	setLayout(new GroupLayout());
    	add(getJRadioButton0(), new Constraints(new Leading(44, 12, 12), new Leading(7, 10, 10)));
    	add(getJRadioButton1(), new Constraints(new Leading(182, 10, 10), new Leading(7, 12, 12)));
    	add(getJCheckBox0(), new Constraints(new Leading(44, 245, 12, 12), new Leading(34, 10, 10)));
    	add(getJCheckBox1(), new Constraints(new Leading(44, 123, 12, 12), new Leading(56, 22, 10, 10)));
    	add(getJButton0(), new Constraints(new Leading(8, 313, 10, 10), new Leading(84, 46, 10, 10)));
    	initButtonGroup1();
    	setSize(328, 137);
    }

    private void initButtonGroup1() {
    	buttonGroup1 = new ButtonGroup();
    	buttonGroup1.add(getJRadioButton1());
    	buttonGroup1.add(getJRadioButton0());
    }

    private JRadioButton getJRadioButton1() {
    	if (jRadioButton1 == null) {
    		jRadioButton1 = new JRadioButton();
    		jRadioButton1.setSelected(true);
    		jRadioButton1.setText("Widescreen");
    	}
    	return jRadioButton1;
    }

    private JCheckBox getJCheckBox1() {
    	if (jCheckBox1 == null) {
    		jCheckBox1 = new JCheckBox();
    		jCheckBox1.setText("Disable Audio");
    	}
    	return jCheckBox1;
    }

    private JRadioButton getJRadioButton0() {
    	if (jRadioButton0 == null) {
    		jRadioButton0 = new JRadioButton();
    		jRadioButton0.setText("Normal");
    	}
    	return jRadioButton0;
    }

    private JButton getJButton0() {
    	if (jButton0 == null) {
    		jButton0 = new JButton();
    		jButton0.setText("Play");
    	}
    	return jButton0;
    }

    private JCheckBox getJCheckBox0() {
    	if (jCheckBox0 == null) {
    		jCheckBox0 = new JCheckBox();
    		jCheckBox0.setText("Start in Fullscreen");
    	}
    	return jCheckBox0;
    }

    private static void installLnF() {
        try {
            String lnfClassname = PREFERRED_LOOK_AND_FEEL;
            if (lnfClassname == null)
                lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(lnfClassname);
        } catch (Exception e) {
            System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL + " on this platform:"
                    + e.getMessage());
        }
    }

    /**
     * Main entry of the class.
     * Note: This class is only created so that you can easily preview the result at runtime.
     * It is not expected to be managed by the designer.
     * You can modify it as you like.
     */
    public static void main(String[] args) {
        installLnF();
        SwingUtilities.invokeLater(new Runnable() {    
            @Override
            public void run() {
                Launcher dialog = new Launcher();
                dialog.setDefaultCloseOperation(Launcher.DISPOSE_ON_CLOSE);
                dialog.setTitle("Arena TCG Launcher");
                dialog.setLocationRelativeTo(null);
                dialog.getContentPane().setPreferredSize(dialog.getSize());
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

}
