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

package de.bonk.tresor.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import de.bonk.tresor.Application;
import de.bonk.tresor.I18N;
import de.bonk.tresor.gui.MainWindow;



/**
 * This is the action listener for the menu item File-&gt;Exit.
 * @author Thomas Bonk
 * @version 0.1
 */
public class ExitAction extends AbstractAction
{
  /**
   * the main window
   */
  private MainWindow mainWindow = null;



  /**
   * Default constructor.
   * @param mainWindow the application window
   */
  public ExitAction( MainWindow mainWindow )
  {
    super( (String)I18N.getInstance().get( mainWindow, "fileMenu.exitItem" ) );
    this.mainWindow = mainWindow;
  }



  public void actionPerformed( ActionEvent e )
  {
    Application.getInstance().exit( 0 );
  }
}