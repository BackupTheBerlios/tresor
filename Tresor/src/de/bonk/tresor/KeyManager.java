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

import java.security.KeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.bonk.tresor.gui.InitialPasswordDialog;
import de.bonk.tresor.gui.PasswordDialog;


/**
 * This class manages the encryption and the store key at the runtime of the
 * application. This class is a singleton.
 * @author Thomas Bonk
 * @version 0.1
 */
public class KeyManager
{
  /**
   * the singleton instance
   */
  private static KeyManager instance = null;

  /**
   * the password store for which this key manager manages the store password
   */
  private AccountStore accountStore = null;

  /**
   * This array holds the password. Get with getPassword and release it with
   * unloadPassword after usage
   */
  private char[] passWord = null;


  /**
   * This method creates the singleton instance. If the given store doesn't
   * have a password hash, the user will be prompted to seta password.
   * @param store the password store
   */
  public static void createInstance( AccountStore store )
  {
    if( null == instance )
        instance = new KeyManager( store );
  }


  /**
   * This method returns the singleton instance.
   * @return the singleton instance
   */
  public static KeyManager getInstance()
  {
    return instance;
  }


  /**
   * Private constructor. If the given store doesn't
   * have a password hash, the user will be prompted to seta password.
   * @param store the password store
   */
  private KeyManager( AccountStore store )
  {
    accountStore = store;

    if( null == accountStore.getPasswordHash() )
    {
      // the account store doesn't have a password; so the user must provide one
      InitialPasswordDialog initPwdDlg = new InitialPasswordDialog();
      setPassword( initPwdDlg.getPassword() );
      initPwdDlg = null;
    }
  }


  /**
   * This method returns the account store.
   * @return the account store
   */
  public AccountStore getAccountStore()
  {
    return this.accountStore;
  }


  /**
   * Getter for the password. If it isn't available anymore, request it from
   * the user. It must be released with unlockPassword after usage.
   * @return character array with the password
   */
  public char[] getPassword() throws KeyException
  {
    if( null == passWord )
    {
      PasswordDialog passwordDialog = new PasswordDialog();
      passWord = passwordDialog.getPassword();

      if( !checkPassword( passWord ) )
      {
        PasswordTool.wipeout( passWord );
        passWord = null;

        throw new KeyException( (String)I18N.getInstance().get( this, "wrongPassword" ) );
      }
    }

    return passWord;
  }


  /**
   * This method unlocks the password.
   */
  public void unlockPassword()
  {
    PasswordTool.wipeout( passWord );
    passWord = null;
  }


  /**
   * This method sets the password for the account store. The character array
   * containing the password will be wiped out after its usage.
   * @param password the password
   */
  private void setPassword( char[] password )
  {
    try
    {
      MessageDigest md5   = MessageDigest.getInstance( "MD5" );
      byte[]        bytes = PasswordTool.convertToByteArray( password );
      md5.update( bytes );
      accountStore.setPasswordHash( md5.digest() );

      PasswordTool.wipeout( bytes );
      PasswordTool.wipeout( password );
    }
    catch( NoSuchAlgorithmException x )
    {
      x.printStackTrace();
    }
  }


  /**
   * This method checks whether the given password is the one for the account
   * store. The character array containing the password will be wiped out after
   * its usage.
   * @param password the password
   * @return true if the password is correct; else false
   */
  private boolean checkPassword( char[] password )
  {
    boolean flOk = false;
    try
    {
      MessageDigest md5   = MessageDigest.getInstance( "MD5" );
      byte[]        bytes = PasswordTool.convertToByteArray( password );
      md5.update( bytes );
      PasswordTool.wipeout( bytes );
      bytes = md5.digest();
      flOk = PasswordTool.equal(bytes,accountStore.getPasswordHash());
      PasswordTool.wipeout( bytes );
    }
    catch( NoSuchAlgorithmException x )
    {
      x.printStackTrace();
    }

    return flOk;
  }





  /* ------------------------------------------------------------------------ *
   * Password related dialogs                                                 *
   * ------------------------------------------------------------------------ */






}
