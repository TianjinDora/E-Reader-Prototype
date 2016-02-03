package eecs285.proj4.shuochen;

//Programmer: Shuo Chen
//Date: November 2015
//Purpose: Demonstrate the use of GUI to support a prototype of a book
//       browser, an application that is similar to an E-Reader.
//       This project will involve developing multiple types of layouts
//       and action listener that will serve our clients.

import javax.swing.JFrame;

public class BookBrowserDemo
{
  public static void main(String[] args)
  {
    //Develop a prototype
    BookBrowserPrototype prototype;
    prototype = new BookBrowserPrototype("Book Browser Prototype");
    
    prototype.pack();    //resize the window
    
    prototype.setVisible(true);
    prototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
