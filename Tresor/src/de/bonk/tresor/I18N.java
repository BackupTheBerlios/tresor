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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.lang.StringBuffer;



/**
 * This class is used to ease the internationalization. This class is a
 * singleton.
 * @author Thomas Bonk
 * @version 0.1
 */
public class I18N
{
  /**
   * This is the singleton instance of the I18N class.
   */
  private static I18N instance = null;


  /**
   * Resource bundle with the langaueg specifiv values
   */
  private PropertyResourceBundle resourceBundle = null;



  /**
   * This class method creates the instance of this class if it is not available
   * yet.
   */
  public static void createInstance() throws IOException
  {
    if( null == instance )
      instance = new I18N();
  }


  /**
   * This class method returns the instance of this class.
   */
  public static I18N getInstance()
  {
    return instance;
  }


  /**
   * Default constructor. This constructor loads a language specific property
   * file that is located in the de.bonk.tresor.resources package. The default
   * property file is called &quote;en.locale&quote;. The filename must follow
   * this scheme: <tt><it>&lt;language&gt;</it>.locale</tt> where
   * <tt><it>&lt;language&gt;</it></tt> is the abbreviation for the language,
   * e.g. <tt>de</tt> for German, <tt>fr</tt> for French etc.
   */
  private I18N() throws IOException
  {
    Locale defaultLocale = Locale.getDefault();
    URL    resourceURL   = null;

    resourceURL = this.getClass().getResource( "/de/bonk/tresor/resources/" +
                                               defaultLocale.getLanguage()  +
                                               ".locale"                      );

    if( null == resourceURL )
        // Language specific resource bundle is not available,
        // so take the default (i.e. English resource bundle)
        resourceURL = this.getClass().getResource(
                        "/de/bonk/tresor/resources/en.locale"
                      );

    // get a buffered input stream for the resource file
    BufferedInputStream bis;
    bis = new BufferedInputStream( resourceURL.openStream() );

    // create the resource bundle
    resourceBundle = new PropertyResourceBundle( bis );

    bis.close();
  }


  /**
   * This method returns the value for the class of the given object and name.
   * @param object The object that requests the value
   * @param key    The key of the value
   * @return The value for the class of the given object and name.
   */
  public Object get( Object object, String key )
  {
    // build the name for key in the resource bundle
    StringBuffer stringBuffer = new StringBuffer( object.getClass().getName() );
    stringBuffer.append( "." );
    stringBuffer.append( key );

    return resourceBundle.getObject( stringBuffer.toString() );
  }
}