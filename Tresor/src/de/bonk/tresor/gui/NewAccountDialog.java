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
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.bonk.gui.GridBagTool;
import de.bonk.tresor.Account;
import de.bonk.tresor.AccountStore;
import de.bonk.tresor.I18N;
import de.bonk.tresor.KeyManager;
import de.bonk.tresor.gui.MainWindow;
import de.bonk.tresor.PasswordTool;


/**
 * This dialog enables the user create a new account.
 * @author Thomas Bonk
 * @version 0.1
 */
public class NewAccountDialog extends JDialog
{
  /**
   * textfield for the description of the account
   */
  private JTextField accountDescription = null;

  /**
   * textfield for the hostname
   */
  private JTextField hostName = null;

  /**
   * textfield for the username
   */
  private JTextField userName = null;

  /**
   * passwordfield for the password
   */
  private JPasswordField passWord = null;

  /**
   * textarea for the notes
   */
  private JTextArea notes = null;



  /**
   * Default constructor.
   */
  public NewAccountDialog()
  {
    super( MainWindow.getInstance() );
    setupGui();
  }


  /**
   * This method sets up the gui for this dialog
   */
  private void setupGui()
  {
    I18N i18n = I18N.getInstance();

    this.setTitle( (String)i18n.get( this, "dialogTitle" ) );
    this.setModal( true );
    this.getContentPane().setLayout( new BorderLayout() );

    JLabel l;
    JPanel panel = new JPanel( new GridBagLayout() );

    l = new JLabel( (String)i18n.get( this, "descriptionLabel" ) );
    GridBagTool.add( panel, l,
                     0, 0, 1, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );
    accountDescription = new JTextField( 30 );
    GridBagTool.add( panel, accountDescription,
                     1, 0, 1, 1,
                     GridBagConstraints.HORIZONTAL,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );

    l = new JLabel( (String)i18n.get( this, "hostnameLabel" ) );
    GridBagTool.add( panel, l,
                     0, 1, 1, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );
    hostName = new JTextField( 30 );
    GridBagTool.add( panel, hostName,
                     1, 1, 1, 1,
                     GridBagConstraints.HORIZONTAL,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );

    l = new JLabel( (String)i18n.get( this, "usernameLabel" ) );
    GridBagTool.add( panel, l,
                     0, 2, 1, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );
    userName = new JTextField( 30 );
    GridBagTool.add( panel, userName,
                     1, 2, 1, 1,
                     GridBagConstraints.HORIZONTAL,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );

    l = new JLabel( (String)i18n.get( this, "passwordLabel" ) );
    GridBagTool.add( panel, l,
                     0, 3, 1, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );
    passWord = new JPasswordField( 30 );
    GridBagTool.add( panel, passWord,
                     1, 3, 1, 1,
                     GridBagConstraints.HORIZONTAL,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 5, 5               );

    l = new JLabel( (String)i18n.get( this, "notesLabel" ) );
    GridBagTool.add( panel, l,
                     0, 4, 2, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     5, 5, 0, 5               );
    notes = new JTextArea( 5, 30 );
    GridBagTool.add( panel, new JScrollPane( notes ),
                     0, 5, 2, 1,
                     GridBagConstraints.BOTH,
                     GridBagConstraints.WEST,
                     0.0, 0.0,
                     0, 5, 15, 5               );

    JPanel  buttonPanel  = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
    JButton buttonOk     = new JButton( (String)i18n.get( this, "buttonOk" ) );
    JButton buttonCancel = new JButton( (String)i18n.get( this, "buttonCancel" ) );
    buttonOk.addActionListener( new OkButtonActionListener() );
    buttonCancel.addActionListener( new CancelButtonActionListener() );
    buttonPanel.add( buttonOk );
    buttonPanel.add( buttonCancel );
    GridBagTool.add( panel, buttonPanel,
                     0, 6, 2, 1,
                     GridBagConstraints.NONE,
                     GridBagConstraints.CENTER,
                     0.0, 0.0,
                     0, 0, 5, 0                  );

    this.getContentPane().add( panel, BorderLayout.CENTER );
    this.getRootPane().setDefaultButton( buttonOk );
    this.pack();
    this.setResizable( false );
    this.setVisible( true );
    accountDescription.grabFocus();
  }





  /* ------------------------------------------------------------------------ *
   * Action listeners for the two buttons.                                    *
   * ------------------------------------------------------------------------ */

  /**
   * Action listener for the OK button
   */
  class OkButtonActionListener implements ActionListener
  {
    /**
     * This method is being called, when the user presses the OK button
     * @param ev event object
     */
    public void actionPerformed( ActionEvent ev )
    {
      try
      {
        char[]       storePassWord = KeyManager.getInstance().getPassword();
        KeyGenerator keyGenerator  = KeyGenerator.getInstance( "Rijndael" );

        System.out.println(keyGenerator);
      }
      catch( KeyException x )
      {
        // the user entered an invalid store password
      }
    }
  }


  /**
   * Action listener for the Cancel button
   */
  class CancelButtonActionListener implements ActionListener
  {
    /**
     * This method is being called, when the user presses the Cancel button
     * @param ev event object
     */
    public void actionPerformed( ActionEvent ev )
    {
      PasswordTool.wipeout( passWord.getPassword() );
      dispose();
    }
  }
}