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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * This class encapsulates the application settings.
 * The following settings are available:
 * <ul>
 *   <li>
 *     <b>keyTimeout</b>: Timeout in seconds after which Tresor forgets the
 *     keystore password. The user must enter the password again when she wants
 *     to view any account infos. Set this to 0 to disable the timeout.
 *   </li>
 * </ul>
 * This is class is a singleton.
 * @author Thomas Bonk
 * @version 0.1
 */
public class Configuration extends Properties
{
  /**
   * reference to the singleton
   */
  private static Configuration instance = null;

  /**
   * Object for the configuration file
   */
  private static File configFile = null;



  /**
   * This class method loads the settings
   * @param configDir file object for the configuration directory
   * @param fileName filename of the configuration file
   */
  public static void load( File configDir, String fileName ) throws IOException
  {
    Configuration.configFile = new File( configDir.getAbsolutePath(), fileName );
    FileInputStream fis        = new FileInputStream( configFile );
    instance = new Configuration( fis );
    fis.close();
  }


  /**
   * This method creates a default configuration.
   */
  public static void createDefault()
  {
    if( null == instance )
        instance = new Configuration();
  }


  /**
   * This method returns the singleton instance.
   * @return the singleton instance
   */
  public static Configuration getInstance()
  {
    return instance;
  }


  /**
   * This method saves the configuration to a file
   */
  public void save() throws FileNotFoundException, IOException
  {
    FileOutputStream fos = new FileOutputStream( configFile );
    this.store( fos, "Tresor properties" );
    fos.close();
  }


  /**
   * Constructor that loads the settings from a file.
   * @param fis the FileInputStream where to read the configuration from
   */
  private Configuration( FileInputStream fis ) throws IOException
  {
    super.load( fis );
  }


  /**
   * This constrcutor creates a default configuration
   */
  private Configuration()
  {
    super.setProperty( "keyTimeout", "300" );
  }
}