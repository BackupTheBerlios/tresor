package de.bonk.tresor;

/**
 * This class provides usefull methods for handling passwords.
 * @author Thomas Bonk
 * @version 0.1
 */
public class PasswordTool
{
  /**
   * Minimal length of a store password.
   */
  public static final int MIN_STORE_PASSWORD_LENGTH = 8;



  /**
   * This method wipes out the given character array (i.e. it overwrites with
   * 0s).
   * @param characters the character array to wipe out
   */
  public static void wipeout( char[] characters )
  {
    for( int i = 0, n = characters.length; i < n; i++ )
      characters[i] = 0;
  }


  /**
   * This method wipes out the given bye array (i.e. it overwrites with
   * 0s).
   * @param bytes the byte array to wipe out
   */
  public static void wipeout( byte[] bytes )
  {
    for( int i = 0, n = bytes.length; i < n; i++ )
      bytes[i] = 0;
  }


  /**
   * This method converts the given character array to a byte array
   * @param characterArray the characterArray to convert
   * @param the byte array
   */
  public static byte[] convertToByteArray( char[] characterArray )
  {
    byte[] bytes = new byte[characterArray.length];

    for( int i = 0, n = characterArray.length; i < n; i++ )
      bytes[i] = (byte)characterArray[i];

    return bytes;
  }


  /**
   * This method converts the given byte array to a character array
   * @param byteArray the byteArray to convert
   * @param the character array
   */
  public static char[] convertToCharArray( char[] byteArray )
  {
    char[] chars = new char[byteArray.length];

    for( int i = 0, n = byteArray.length; i < n; i++ )
      chars[i] = (char)byteArray[i];

    return chars;
  }


  /**
   * This methods checks whether two character arrays are equal.
   * @param ca1 the first character array
   * @param ca2 the second character array
   * @return true if the two character arrays are equal
   */
  public static boolean equal( char[] ca1, char[] ca2 )
  {
    if( ca1.length != ca2.length )
        return false;

    for( int i = 0, n = ca1.length; i < n; i++ )
    {
      if( ca1[i] != ca2[i] )
        return false;
    }

    return true;
  }


  /**
   * This methods checks whether two byte arrays are equal.
   * @param b1 the first byte array
   * @param b2 the second byte array
   * @return true if the two byte arrays are equal
   */
  public static boolean equal( byte[] b1, byte[] b2 )
  {
    if( b1.length != b2.length )
        return false;

    for( int i = 0, n = b1.length; i < n; i++ )
    {
      if( b1[i] != b2[i] )
        return false;
    }

    return true;
  }
}