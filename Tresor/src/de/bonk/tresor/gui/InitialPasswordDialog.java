//    Tresor - A Password Manager
//    Copyright (C) 2001 Thomas Bonk
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

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
import de.bonk.tresor.PasswordTool;



/**
 * This is the class for the dialog that requests the initial password for a
 * store.
 * @author Thomas Bonk
 * @version 0.1
 */
public class InitialPasswordDialog extends JDialog implements ActionListener
{
  /**
   * Text field for the password
   */
  private JPasswordField passWord = null;

  /**
   * Textfield for the retyped password
   */
  private JPasswordField retypedPassWord = null;

  /**
   * This array contains the password
   */
  private char[] caPassWord = null;


  /**
   * Default constructor that initialises and opens the modal dialog.
   */
  public InitialPasswordDialog()
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
    l = new JLabel((String)I18N.getInstance().get(this,"labelRetypePassword"));
    GridBagTool.add( pwdPanel, l,
                     0, 1, 1, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     0, 5, 5, 5          );
    retypedPassWord = new JPasswordField( 16 );
    GridBagTool.add( pwdPanel, retypedPassWord,
                     1, 1, 1, 1,
                     GridBagConstraints.HORIZONTAL,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     0, 0, 5, 5          );
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
    char[] caPassWord            = passWord.getPassword();
    char[] caRetypedPassWord     = retypedPassWord.getPassword();

    if( 0 == caPassWord.length )
    {
      passWord.setText( "" );
      passWord.grabFocus();
      JOptionPane.showMessageDialog(
        this,
        (String)I18N.getInstance().get(this,"passwordErrorMessage"),
        (String)I18N.getInstance().get(this,"titleError"),
        JOptionPane.ERROR_MESSAGE                                    );
      return;
    } // if

    if( 0 == caRetypedPassWord.length )
    {
      retypedPassWord.setText( "" );
      retypedPassWord.grabFocus();
      JOptionPane.showMessageDialog(
        this,
        (String)I18N.getInstance().get(this,"retypePasswordErrorMessage"),
        (String)I18N.getInstance().get(this,"titleError"),
        JOptionPane.ERROR_MESSAGE                                    );
      return;
    } // if

    if( !PasswordTool.equal( caPassWord, caRetypedPassWord ) )
    {
      retypedPassWord.setText( "" );
      passWord.setText( "" );
      passWord.grabFocus();
      JOptionPane.showMessageDialog(
        this,
        (String)I18N.getInstance().get(this,"enterAgainErrorMessage"),
        (String)I18N.getInstance().get(this,"titleError"),
        JOptionPane.ERROR_MESSAGE                                    );
      return;
    } // if

    if( caPassWord.length < PasswordTool.MIN_STORE_PASSWORD_LENGTH )
    {
      retypedPassWord.setText( "" );
      passWord.setText( "" );
      passWord.grabFocus();
      JOptionPane.showMessageDialog(
        this,
        (String)I18N.getInstance().get(this,"tooShortErrorMessage"),
        (String)I18N.getInstance().get(this,"titleError"),
        JOptionPane.ERROR_MESSAGE                                    );
      return;
    } // if

    this.caPassWord = caPassWord;
    PasswordTool.wipeout( caRetypedPassWord );
    this.dispose();
  }
} // class InitialPasswordDialog
