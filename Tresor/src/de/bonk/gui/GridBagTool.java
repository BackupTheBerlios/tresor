package  de.bonk.gui;

import  java.awt.Container;
import  java.awt.Component;
import  java.awt.Insets;
import  java.awt.GridBagLayout;
import  java.awt.GridBagConstraints;


/**
 * This class is a helper for laying out components in the
 * GridBagLayout layout manager.
 */
public class GridBagTool {
  /**
   * Add a component to a container.
   * @param container
   * @param component
   * @param grid_x
   * @param grid_y
   * @param grid_width
   * @param grid_height
   * @param fill
   * @param anchor
   * @param weight_x
   * @param weight_y
   * @param top
   * @param left
   * @param bottom
   * @param right
   */
  public static void add ( Container container,
                           Component component,
                           int       grid_x,
                           int       grid_y,
                           int       grid_width,
                           int       grid_height,
                           int       fill,
                           int       anchor,
                           double    weight_x,
                           double    weight_y,
                           int       top,
                           int       left,
                           int       bottom,
                           int       right)
  {
    GridBagConstraints c = new GridBagConstraints();

    c.gridx      = grid_x;
    c.gridy      = grid_y;
    c.gridwidth  = grid_width;
    c.gridheight = grid_height;
    c.fill       = fill;
    c.anchor     = anchor;
    c.weightx    = weight_x;
    c.weighty    = weight_y;
    if( top + bottom + left + right > 0 )
      c.insets = new Insets( top, left, bottom, right );

    ((GridBagLayout)container.getLayout()).setConstraints( component, c );
    container.add( component );
  }

  /**
   * Add a component to a container.
   * @param container
   * @param component
   * @param grid_x
   * @param grid_y
   * @param grid_width
   * @param grid_height
   */
  public static void add ( Container container,
                           Component component,
                           int       grid_x,
                           int       grid_y,
                           int       grid_width,
                           int       grid_height)
  {
    add( container,
         component,
         grid_x,
         grid_y,
         grid_width,
         grid_height,
         GridBagConstraints.NONE,
         GridBagConstraints.NORTHWEST,
         0.0, 0.0, 0, 0, 0, 0          );
  }

  /**
   * Add a component to a container.
   * @param container
   * @param component
   * @param grid_x
   * @param grid_y
   * @param grid_width
   * @param grid_height
   * @param top
   * @param left
   * @param bottom
   * @param right
   */
  public static void add ( Container container,
                           Component component,
                           int grid_x,
                           int grid_y,
                           int grid_width,
                           int grid_height,
                           int top,
                           int left,
                           int bottom,
                           int right              )
  {
    add( container,
         component,
         grid_x,
         grid_y,
         grid_width,
         grid_height,
         GridBagConstraints.NONE,
         GridBagConstraints.NORTHWEST,
         0.0, 0.0,
         top, left, bottom, right       );
  }
}