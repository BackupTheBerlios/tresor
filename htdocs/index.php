<html>

  <head>
    <title>Tresor - A Java(tm) Password Manager</title>
  </head>

  <body bgcolor="#FFFFFF">

    <?
    if( "" == $page )
      $page="/about/about.php";
    if( ("index.php" == $page) || strrpos( $page, "index.php" ) )
      $page="/about/about.php";
    ?>

    <table width="600" border="0" cellspacing="0" cellpadding="2">
      <tr>
        <td colspan="2" bgcolor="#000000">
          <font face="Arial,Helvetica" color="#FFFFFF" size="+2">
            <center><b>Tresor - A Java(tm) Password Manager<b></center>
          </font>
        </td>
      </tr>

      <tr valign="top">

        <td bgcolor="#f9d20e" width="120">
          <font face="Arial,Helvetica">
            <center>
              <table width="100%">
                <tr bgcolor="#000000">
                  <td>

                    <table width="100%">
                      <tr bgcolor="#000000">
                        <td>
                          <font face="Arial,Helvetica" color="#FFFFFF">
                            <center><b>Menu</b></center>
                          </font>
                        </td>
                      </tr>

                      <tr bgcolor="#f9d20e">
                        <td>
                          <font face="Arial,Helvetica">
                            <center>
                              <a href="index.php?page=/about/about.php">About</a><br>
                              <a href="index.php?page=/about/about.php">News</a><br>
                              <a href="http://developer.berlios.de/projects/tresor/">Project page</a>
                            </center>
                          </font>
                        </td>
                      </tr>
                    </table>

                  </td>
                </tr>
              </table>

              <table width="100%">
                <tr bgcolor="#000000">
                  <td>

                    <table width="100%">
                      <tr bgcolor="#000000">
                        <td>
                          <font face="Arial,Helvetica" color="#FFFFFF">
                            <center><b>Contact</b></center>
                          </font>
                        </td>
                      </tr>

                      <tr bgcolor="#f9d20e">
                        <td>
                          <font face="Arial,Helvetica">
                            <center>
                              <a href="mailto:tbonk@mail.berlios.de">Thomas Bonk</a>
                            </center>
                          </font>
                        </td>
                      </tr>
                    </table>

                  </td>
                </tr>
              </table>

              <p>
                <b>Hosted by</b><br>
                <a href="http://developer.berlios.de">
                  <img src="http://developer.berlios.de/sflogo.php?group_id=139&type=1"
                       width="118"
                       height="52"
                       border="0"
                       alt="BerliOS Logo">
                </a>
              </p>

              <p>
                <b>Build on</b><br>
                <a href="http://www.linuxfromscratch.org">
                  <img src="./images/lfs.jpg"
                       width="118"
                       height="50"
                       border="0"
                       alt="Linux From Scratch Logo">
                </a>
              </p>
            </center>
          </font>
        </td>

        <td>
          <font face="Arial,Helvetica">
            <?include( "./".$page );?>
          </font>
        </td>

      </tr>

      <tr>
        <td colspan="2" bgcolor="#000000">
          <font face="Arial,Helvetica" color="#FFFFFF">
            <?
            $filemod = filemtime( "./".$page );
            $filemodtime = date( "F j Y h:i:s A", $filemod );
            Print( "$page last modified $filemodtime" );
            ?>
          </font>
        </td>
      </tr>
    </table>

  </body>

</html>
