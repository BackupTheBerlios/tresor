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
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.AbstractListModel;



public class AccountStore extends AbstractListModel implements Serializable
{
  static final long serialVersionUID = 7649325821760253146L;

  /**
   * Object for the store file
   */
  private static File storeFile = null;

  /**
   * hash value of the store password
   */
  private byte[] passwordHash = null;

  /**
   * this is the set of all accounts
   */
  private SortedSet accounts = null;



  /**
   * Default constructor.
   */
  public AccountStore()
  {
    accounts = new TreeSet();
  }


  /**
   * This method returns the n-th account.
   * @param n index of the account which shall be returned
   * @return the n-th account
   */
  public Object getElementAt( int n )
  {
    Object[] array = accounts.toArray();

    return array[n];
  }


  /**
   * This method returns the number of accounts in the accoutn store.
   * @return numer of accounts in the account store
   */
  public int getSize()
  {
    return accounts.size();
  }


  /**
   * Getter for the hash value of the store password.
   * @return the hash value of the store password
   */
  public byte[] getPasswordHash()
  {
    return this.passwordHash;
  }

  /**
   * Setter for the hash value of the store password.
   * @param passwordHash the hash value of the store password
   */
  public void setPasswordHash( byte[] passwordHash )
  {
    this.passwordHash = passwordHash;
  }


  /**
   * This class methods deserializes a store from a file. If the file doesn't
   * exist an empty account store will be returned
   * @param storeFile file object of the serialized account store
   * @return an account store
   */
  public static AccountStore load( File storeFile ) throws IOException,
                                                           FileNotFoundException,
                                                           StreamCorruptedException,
                                                           ClassNotFoundException
  {
    AccountStore      store;

    AccountStore.storeFile = storeFile;

    if( storeFile.exists() )
    {
      // the store file exists, so load it
      FileInputStream   fis = new FileInputStream( storeFile );
      ObjectInputStream ois = new ObjectInputStream( fis );

      store = (AccountStore)ois.readObject();
      ois.close();
    }
    else
    {
      // there is no store file; a store must be created
      store = new AccountStore();
    }

    return store;
  }


  /**
   * This method serializes the account store to a file
   */
  public void save() throws IOException,
                            FileNotFoundException
  {
    FileOutputStream   fos = new FileOutputStream( storeFile );
    ObjectOutputStream oos = new ObjectOutputStream( fos );

    oos.writeObject( this );
    oos.close();
  }
}