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
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import de.bonk.tresor.Application;
import de.bonk.tresor.Configuration;
import de.bonk.tresor.I18N;
import de.bonk.tresor.KeyManager;
import de.bonk.tresor.actions.ExitAction;
import de.bonk.tresor.actions.NewAccountAction;



/**
 * This is the main window of the application. This class is a singleton.
 * @author Thomas Bonk
 * @version 0.1
 */
public class MainWindow extends JFrame
{
  /**
   * The singleton instance of this class
   */
  private static MainWindow instance = null;

  // the file menu
  private JMenu     menuFile           = null;
  private JMenuItem itemSaveStore      = null;
  private JMenuItem itemChangePassword = null;
  private JMenuItem itemExportStore    = null;
  private JMenuItem itemImportStore    = null;
  private JMenuItem itemExit           = null;

  // the edit menu
  private JMenu     menuEdit          = null;
  private JMenuItem itemViewAccount   = null;
  private JMenuItem itemNewAccount    = null;
  private JMenuItem itemEditAccount   = null;
  private JMenuItem itemDeleteAccount = null;

  /**
   * list box for the account desccriptions
   */
  private JList accountDescriptions = null;


  /**
   * the label for the status line
   */
  private JLabel statusLine = null;



  /**
   * This method creates the singleton instance
   */
  public static void createInstance()
  {
    if( null == instance )
      instance = new MainWindow();
  }


  /**
   * This method returns the singleton of this class
   * @return the singleton instance
   */
  public static MainWindow getInstance()
  {
    return instance;
  }


  /**
   * Default constructor that sets up the window
   */
  private MainWindow()
  {
    super( "Tresor" );
    this.addWindowListener( new MainWindowAdapter() );

    this.setJMenuBar( createMenuBar() );

    // setup the status line
    this.getContentPane().setLayout( new BorderLayout() );
    statusLine = new JLabel( "Tresor " + Application.VERSION + " ready." );
    statusLine.setBorder( new BevelBorder( BevelBorder.LOWERED ) );
    this.getContentPane().add( statusLine, BorderLayout.SOUTH );

    accountDescriptions = new JList(KeyManager.getInstance().getAccountStore());
    this.getContentPane().add( new JScrollPane( accountDescriptions ),
                               BorderLayout.CENTER                     );

    Configuration theConfiguration = Configuration.getInstance();
    this.setSize( theConfiguration.getWindowWidth(),
                  theConfiguration.getWindowHeight() );
    this.setLocation( theConfiguration.getWindowXPos(),
                      theConfiguration.getWindowYPos() );
    this.setVisible( true );
  }


  /**
   * This method sets the text in the status line
   */
  public void setStatusLineText( String text )
  {
    statusLine.setText( text );
  }


  /**
   * This method stores the window position and size in the configuration.
   */
  public void saveWindowProperties()
  {
    // set the current window position and size in the configuration
    Configuration theConfiguration = Configuration.getInstance();
    Point         theLocation      = this.getLocation();
    Dimension     theSize          = this.getSize();
    theConfiguration.setWindowXPos( (int)theLocation.getX() );
    theConfiguration.setWindowYPos( (int)theLocation.getY() );
    theConfiguration.setWindowWidth( (int)theSize.getWidth() );
    theConfiguration.setWindowHeight( (int)theSize.getHeight() );
  }


  /**
   * This method creates the menu bar.
   * @return The menu bar.
   */
  private JMenuBar createMenuBar()
  {
    I18N     i18n    = I18N.getInstance();
    JMenuBar menuBar = new JMenuBar();

    // create the file menu
    menuFile = new JMenu( (String)i18n.get( this, "fileMenu" ) );
    itemSaveStore = new JMenuItem(
                          (String)i18n.get( this, "fileMenu.saveItem" )
                    );
    menuFile.add( itemSaveStore );
    menuFile.addSeparator();
    itemChangePassword = new JMenuItem(
                               (String)i18n.get( this, "fileMenu.changePasswordItem" )
                         );
    menuFile.add( itemChangePassword );
    menuFile.addSeparator();
    itemImportStore = new JMenuItem(
                            (String)i18n.get( this, "fileMenu.importItem" )
                      );
    menuFile.add( itemImportStore );
    itemExportStore = new JMenuItem(
                            (String)i18n.get( this, "fileMenu.exportItem" )
                      );
    menuFile.add( itemExportStore );
    menuFile.addSeparator();
    itemExit = new JMenuItem( new ExitAction( this ) );
    menuFile.add( itemExit );
    menuBar.add( menuFile );

    // create the edit file menu
    menuEdit = new JMenu( (String)i18n.get( this, "editMenu" ) );
    itemViewAccount = new JMenuItem(
                           (String)i18n.get( this, "editMenu.viewAccountItem" )
                     );
    menuEdit.add( itemViewAccount );
    itemNewAccount = new JMenuItem( new NewAccountAction( this ) );
    menuEdit.add( itemNewAccount );
    itemEditAccount = new JMenuItem(
                            (String)i18n.get( this, "editMenu.editAccountItem" )
                      );
    menuEdit.add( itemEditAccount );
    itemDeleteAccount = new JMenuItem(
                              (String)i18n.get( this, "editMenu.deleteAccountItem" )
                        );
    menuEdit.add( itemDeleteAccount );
    menuBar.add( menuEdit );

    return menuBar;
  }
}