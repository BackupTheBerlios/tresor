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

package de.bonk.tresor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;

import cryptix.jce.provider.CryptixCrypto;

import de.bonk.tresor.gui.MainWindow;



/**
 * <p>
 *   Tresor is a small utility that helps its users to manage their accounts and
 *   the passwords for these accounts. The sensible data will be encrypted.
 * </p>
 * <p>
 *   This is the application class that contains the entry point. This class is
 *   a singleton.
 * </p>
 * @author Thomas Bonk
 * @version 0.1
 */
public class Application
{
  /**
   * Version of the application
   */
  public static final String VERSION = "0.1";

  /**
   * Name of the configuration directory
   */
  public static final String CONFIG_DIR = ".tresor";

  /**
   * Name of the configuration file
   */
  public static final String CONFIG_FILE = "tresor.rc";

  /**
   * Name of the password store
   */
  public static final String STORE_FILE = "tresor.store";

  /**
   * This is the singleton instance of the application
   */
  private static Application instance = null;

  /**
   * Application window
   */
  private MainWindow mainWindow = null;

  /**
   * The accoutn store
   */
  private AccountStore accountStore = null;



  static
  {
    // add the cryptix jce provider
    Security.addProvider( new CryptixCrypto() );
  }



  /**
   * Entry point of the application.
   * @param args command line parameters
   */
  public static void main( String[] args )
  {
    Application app = Application.getInstance();
  }


  /**
   * This method returns the application instance. If there is no instance yet,
   * it will be created.
   * @return Singleton instance of the Application object.
   */
  public static Application getInstance()
  {
    if( null == instance )
        instance = new Application();
    return instance;
  }


  /**
   * This method terminates the application.
   * @param code return code
   */
  public void exit( int code )
  {
    try
    {
      mainWindow.saveWindowProperties();
      Configuration.getInstance().save();
      accountStore.save();
    }
    catch( IOException x )
    {
      System.out.println( "I/O error during application termination!" );
      x.printStackTrace();
    }

    System.exit( code );
  }


  /**
   * Default constructor.
   */
  private Application()
  {
    try
    {
      I18N.createInstance();
    }
    catch( IOException x )
    {
      System.out.println( "Unable to load the default resource bundle. " +
                          "The application will be terminated."            );
      x.printStackTrace();
      System.exit( -1 );
    }

    //MainWindow.createInstance();
    //mainWindow = MainWindow.getInstance();

    // check whether the configuration directory exists and create it, if it
    // didn't exist
    File configDir = new File( System.getProperty("user.home"), CONFIG_DIR );
    checkConfigDir( configDir );

    try
    {
      // load the configuration
      Configuration.load( configDir, CONFIG_FILE );
    }
    catch( IOException x )
    {
      // no configuration found; so create the default configuration
      Configuration.createDefault();
    }


    // try to load the password store
    File storeFile = new File( configDir.getAbsolutePath(), STORE_FILE );
    try
    {
      accountStore = AccountStore.load( storeFile );
      KeyManager.createInstance( accountStore );
    }
    catch( ClassNotFoundException x )
    {
      System.out.println( "INTERNAL ERROR: A class was not found!" );
      x.printStackTrace();
      System.exit( -1 );
    }
    catch( IOException x )
    {
      System.out.println( "Unable to load the account store." );
      x.printStackTrace();
      System.exit( -1 );
    }

    MainWindow.createInstance();
    mainWindow = MainWindow.getInstance();
  }


  /**
   * checks whether the configuration directory exists; if not it will be
   * created.
   * @param configDir file object for the configuration directory
   * @return true if the config dir existed
   */
  private boolean checkConfigDir( File configDir )
  {
    boolean existed;

    if( configDir.exists() && configDir.isDirectory() )
    { // $HOME/.tresor exists and it is a directory
      existed = true;
    } // if
    else
    { // $HOME/.tresor either doesn't exist or it is a file
      if( !configDir.exists() )
        // $HOME/.tresor doesn't exist; so create it
        configDir.mkdir();
      else if( configDir.isFile() )
      {
        // $HOME/.tresor exists but it is a file; delete the file and create
        // the directory
        configDir.delete();
        configDir.mkdir();
      }

      existed = false;
    } // else

    return existed;
  }
}
