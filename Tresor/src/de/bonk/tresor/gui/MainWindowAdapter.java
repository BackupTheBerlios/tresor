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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.bonk.tresor.Application;


public class MainWindowAdapter extends    WindowAdapter
                               implements WindowListener
{
  /**
   * Default constructor.
   */
  public MainWindowAdapter()
  {
  }


  /**
   * This method is being called, when the main window is in the closing
   * process.
   * @param e the event object
   */
  public void windowClosing( WindowEvent e )
  {
    Application.getInstance().exit( 0 );
  }
}