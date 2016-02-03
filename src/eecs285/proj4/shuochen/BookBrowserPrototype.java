package eecs285.proj4.shuochen;

//Programmer: Shuo Chen
//Date: November 2015
//Purpose: Demonstrate the use of GUI to support a prototype of a book
//         browser, an application that is similar to an E-Reader.
//         This project will involve developing multiple types of layouts
//         and action listener that will serve our clients.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.net.URL;

//The frame of the prototype
public class BookBrowserPrototype extends JFrame
{
  public static final int TEXT_LENGTH = 20;// The length of the text field
  // The combo box
  private JComboBox<String> bookTitleComb = new JComboBox<String>();
  // The labels
  private JLabel authorLabel = new JLabel("Author", SwingConstants.RIGHT);
  private JLabel titleLabel = new JLabel("  Title   ", SwingConstants.RIGHT);
  // The text fields
  private JTextField authorField = new JTextField(TEXT_LENGTH);
  private JTextField titleField = new JTextField(TEXT_LENGTH);
  // The buttons
  private JButton authorSearchButton = new JButton("search");
  private JButton titleSearchButton = new JButton("search");
  private JButton leftButton = new JButton("<");
  private JButton leftmostButton = new JButton("<<<");
  private JButton rightButton = new JButton(">");
  private JButton rightmostButton = new JButton(">>>");
  // Insert Images
  private URL imgURL = getClass().getResource("/images/NoBookSelected.png");
  private JLabel imgLabel = new JLabel(new ImageIcon(imgURL, "No Book"));

  private String bookTitle[] =
  { "The Berenstain Bears' Thanksgiving",
      "There Was an Old Lady Who Swallowed a Chick",
      "Pete the cat and His Magic Sunglasses" }; // Input the full names of each
                                                 // book.
  private String photoName[] =
  { "berenstain", "oldLady", "pete" }; // Input the short names of the image
                                       // inserted of every book.
  private int totalPage[] =
  { 4, 4, 4 }; // Input the total pages of each book.
  private int recentPage[] =
  { 1, 1, 1 }; // Initialize the last page viewed to 1.
  private int currentBook = -1;// Initialize the current book to "no selection"
  private BookBrowserListener bookBrowser = new BookBrowserListener();
  private ComboBoxListener comboBox = new ComboBoxListener();

  public BookBrowserPrototype(String inTitle)
  {
    super(inTitle);
    // The upper panel and the panels included in it.
    JPanel upperPan = new JPanel(new BorderLayout());
    JPanel comboBoxPan = new JPanel(new FlowLayout());
    JPanel authorPan = new JPanel(new FlowLayout());
    JPanel titlePan = new JPanel(new FlowLayout());

    // The lower panel and the panels included in it.
    JPanel lowerPan = new JPanel(new FlowLayout());
    JPanel imagePan = new JPanel(new FlowLayout());
    JPanel leftPan = new JPanel(new BorderLayout());
    JPanel rightPan = new JPanel(new BorderLayout());

    setLayout(new BorderLayout());

    // Upper Panel

    // Combo Box
    bookTitleComb.addItem("No Book Selected");// Add the null selection
    // Add the title of every book
    for (int book = 0; book < bookTitle.length; book++ )
    {
      bookTitleComb.addItem(bookTitle[book]);
    }
    ((JLabel) bookTitleComb.getRenderer())
        .setHorizontalAlignment(SwingConstants.CENTER);
    comboBoxPan.add(bookTitleComb);
    // Author Panel
    authorPan.add(authorLabel);
    authorPan.add(authorField);
    authorPan.add(authorSearchButton);
    // Title Panel
    titlePan.add(titleLabel);
    titlePan.add(titleField);
    titlePan.add(titleSearchButton);

    upperPan.add(comboBoxPan, BorderLayout.NORTH);
    upperPan.add(authorPan, BorderLayout.CENTER);
    upperPan.add(titlePan, BorderLayout.SOUTH);
    add(upperPan, BorderLayout.NORTH);

    // Lower Panel

    // Left Panel
    leftPan.add(leftButton, BorderLayout.NORTH);
    leftPan.add(leftmostButton, BorderLayout.SOUTH);
    // Image Panel
    imagePan.add(imgLabel);
    // Right Panel
    rightPan.add(rightButton, BorderLayout.NORTH);
    rightPan.add(rightmostButton, BorderLayout.SOUTH);

    lowerPan.add(leftPan);
    lowerPan.add(imagePan);
    lowerPan.add(rightPan);
    add(lowerPan, BorderLayout.CENTER);

    // Add all action listeners to buttons.
    authorSearchButton.addActionListener(bookBrowser);
    titleSearchButton.addActionListener(bookBrowser);
    leftButton.addActionListener(bookBrowser);
    leftmostButton.addActionListener(bookBrowser);
    rightButton.addActionListener(bookBrowser);
    rightmostButton.addActionListener(bookBrowser);
    bookTitleComb.addActionListener(comboBox);
  }// end of constructor

  private class BookBrowserListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      // Action on author search button.
      if (event.getSource() == authorSearchButton)
      {
        authorSearch(authorField.getText());
      }
      // Action on title search button.
      else if (event.getSource() == titleSearchButton)
      {
        titleSearch(titleField.getText());
      }
      // Action on the left button, and no book is selected now.
      else if (event.getSource() == leftButton && currentBook != -1)
      {
        // If the current page is not the first page, update the image to the
        // previous page.
        if (recentPage[currentBook] != 1)
        {
          recentPage[currentBook]-- ;
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
        // If the current page is the first page, update the image to the last
        // page.
        else
        {
          recentPage[currentBook] = totalPage[currentBook];
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
      }
      // Action on the left-most button, and no book is selected now.
      else if (event.getSource() == leftmostButton && currentBook != -1)
      {
        // If the current page is not the first page, update the image to the
        // first page.
        if (recentPage[currentBook] != 1)
        {
          recentPage[currentBook] = 1;
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
      }
      // Action on the right button, and no book is selected now.
      else if (event.getSource() == rightButton && currentBook != -1)
      {
        // If the current page is not the last page, update the image to the
        // last page.
        if (recentPage[currentBook] != totalPage[currentBook])
        {
          recentPage[currentBook]++ ;
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
        // If the current page is the last page, update the image to the first
        // page.
        else
        {
          recentPage[currentBook] = 1;
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
      }
      // Action on the right-most button, and no book is selected now.
      else if (event.getSource() == rightmostButton && currentBook != -1)
      {
        // If the current page is not the last page
        if (recentPage[currentBook] != totalPage[currentBook])
        {
          recentPage[currentBook] = totalPage[currentBook];
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
      }
    }
  }

  // This method is the action listener of the combo box.
  private class ComboBoxListener implements ActionListener
  {
    // When action is performed
    public void actionPerformed(ActionEvent event)
    {
      // Get the title of the book selected.
      Object selectedItem = ((JComboBox<String>) event.getSource())
          .getSelectedItem();
      // Iterate all books
      for (int book = 0; book < bookTitle.length; book++ )
      {
        // Check if the book is selected
        if (selectedItem == bookTitle[book])
        {
          // Update the image to the selected book, the page should be the last
          // page the user viewed for that book.
          currentBook = book;
          updateImg(photoName[currentBook], recentPage[currentBook]);
        }
      }
      // If no book selected
      if (selectedItem == "No Book Selected")
      {
        currentBook = -1;
        imgURL = getClass().getResource("/images/NoBookSelected.png");
        imgLabel.setIcon(new ImageIcon(imgURL, "No Book"));
      }
    }
  }

  // This method pops up a window saying searching isn't implemented at this
  // time.
  private void showMessage()
  {
    JOptionPane.showMessageDialog(null,
        "Searching isn't implemented at this time.");
  }

  // This method updates the image to the correct image in the next stage.
  // The function includes the parameters name and page.
  // name is the short name of the book; page is the page in the book.
  private void updateImg(String name, int page)
  {
    imgURL = getClass().getResource("/images/" + name + page + ".jpg");
    imgLabel.setIcon(new ImageIcon(imgURL, name + page));
  }

  // This method implements the search of author.
  private void authorSearch(String text)
  {
    showMessage(); // Show the message dialog
  }

  // This method implements the search of title.
  private void titleSearch(String text)
  {
    showMessage(); // Show the message dialog
  }
}
