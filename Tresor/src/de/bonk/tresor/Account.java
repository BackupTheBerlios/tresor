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

import java.io.Serializable;
import java.text.Collator;



/**
 * This class represents an account. All data except the description is
 * encrypted.
 * @author Thomas Bonk
 * @version 0.1
 */
public class Account implements Serializable, Comparable
{
  static final long serialVersionUID = -7297964106192903325L;

  /**
   * description of the account
   */
  private String description;

  /**
   * encrypted hostname
   */
  private byte[] hostname;

  /**
   * encrypted username
   */
  private byte[] username;

  /**
   * encrypted password
   */
  private byte[] password;

  /**
   * encrypted notes
   */
  private byte[] notes;



  /**
   * Default constructor.
   */
  public Account()
  {
  }


  /**
   * Compares this object with the specified object for order. Returns a
   * negative integer, zero, or a positive integer as this object is less than,
   * equal to, or greater than the specified object.
   * @param o the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is
   *         less than, equal to, or greater than the specified object.
   */
  public int compareTo( Object o )
  {
    Collator collator = Collator.getInstance();

    return collator.compare( description, ((Account)o).description );
  }


  /**
   * Getter for the account description.
   * @return account description
   */
  public String getDescription()
  {
    return this.description;
  }

  /**
   * Setter for the account description.
   * @param description the account description
   */
  public void setDescription( String description )
  {
    this.description = description;
  }


  /**
   * Getter for the hostname of the account.
   * @return the hostname as an encrypted byte stream
   */
  public byte[] getHostname()
  {
    return this.hostname;
  }

  /**
   * Setter for the hostname of the account.
   * @param hostname the hostname as an encrypted byte stream
   */
  public void setHostname( byte[] hostname )
  {
    this.hostname = hostname;
  }


  /**
   * Getter for the account's user name.
   * @return the user name as an encrypted byte stream
   */
  public byte[] getUsername()
  {
    return this.username;
  }

  /**
   * Setter for the account's user name.
   * @param username the user name as an encrypted byte stream
   */
  public void setUsername( byte[] username )
  {
    this.username = username;
  }


  /**
   * Getter for the account's password
   * @return the password as an encrypted byte stream
   */
  public byte[] getPassword()
  {
    return this.password;
  }

  /**
   * Getter for the account's password
   * @param password the password as an encrypted byte stream
   */
  public void setPassword( byte[] password )
  {
    this.password = password;
  }


  /**
   * Getter for the account notes
   * @return the account notes as an encrypted byte stream
   */
  public byte[] getNotes()
  {
    return this.notes;
  }

  /**
   * Setter for the account notes
   * @param notes the account notes as an encrypted byte stream
   */
  public void setNotes( byte[] notes )
  {
    this.notes = notes;
  }
}