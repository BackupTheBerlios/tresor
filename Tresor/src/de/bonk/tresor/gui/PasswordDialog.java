package de.bonk.tresor.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import de.bonk.gui.GridBagTool;
import de.bonk.tresor.I18N;



/**
   * This is the class for the dialog that requests the initial password for a
   * store.
   * @author Thomas Bonk
   * @version 0.1
   */
  public class PasswordDialog extends JDialog implements ActionListener
  {
    /**
     * Text field for the password
     */
    private JPasswordField passWord = null;

    /**
     * This array contains the password
     */
    private char[] caPassWord = null;


    /**
     * Default constructor that initialises and opens the modal dialog.
     */
    public PasswordDialog()
    {
      super( MainWindow.getInstance() );
      this.setTitle( (String)I18N.getInstance().get( this, "title" ) );
      this.setModal( true );
      this.getContentPane().setLayout( new BorderLayout() );

      JPanel pwdPanel = new JPanel( new GridBagLayout() );
      JLabel l;
      l = new JLabel( (String)I18N.getInstance().get(this,"labelPassword") );
      GridBagTool.add( pwdPanel, l,
                       0, 0, 1, 1,
                       GridBagConstraints.NONE,
                       GridBagConstraints.WEST,
                       0.0, 0.0,
                       5, 5, 5, 5          );
      passWord = new JPasswordField( 16 );
      GridBagTool.add( pwdPanel, passWord,
                       1, 0, 1, 1,
                       GridBagConstraints.HORIZONTAL,
                       GridBagConstraints.WEST,
                       0.0, 0.0,
                       5, 0, 5, 5          );
      JButton buttonOk = new JButton((String)I18N.getInstance().get(this,"buttonOk"));
      buttonOk.addActionListener( this );
      GridBagTool.add( pwdPanel, buttonOk,
                       0, 2, 2, 1,
                       GridBagConstraints.NONE,
                       GridBagConstraints.CENTER,
                       0.0, 0.0,
                       5, 5, 5, 5          );
      this.getContentPane().add( pwdPanel, BorderLayout.CENTER );

      passWord.grabFocus();
      buttonOk.setDefaultCapable( true );
      this.getRootPane().setDefaultButton( buttonOk );

      this.pack();
      this.setResizable( false );
      this.setVisible( true );
    }


    /**
     * This method returns th clear text password
     * @return the cleartext password
     */
    public char[] getPassword()
    {
      return this.caPassWord;
    }


    /**
     * This method is being called, when the user presses the OK button
     * @param ev event object
     */
    public void actionPerformed( ActionEvent ev )
    {
      this.caPassWord = passWord.getPassword();
      this.dispose();
    }
  } // class InitialPasswordDialog