package GUI;

import javax.swing.*;
import java.awt.*;

public class LeftToolbar extends JPanel {

    private JButton searchButton ;
    private JButton libraryButton ;
    private JButton recentPlaysButton ;
    private JButton playListsButton ;
    private JButton favoritesButton ;
    private JButton addToLibraryButton ;
    private JPanel searchField ;

    public LeftToolbar(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL ;

        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Search.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        searchButton.setBackground(new Color(0x4B829C));
        add(searchButton , gbc);

        libraryButton = new JButton();
        libraryButton.setText("My Library");
        libraryButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\MyLibrary.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 1;
        libraryButton.setBackground(new Color(0x4B829C));
        add(libraryButton , gbc) ;

        recentPlaysButton = new JButton();
        recentPlaysButton.setText("Recent Plays");
        recentPlaysButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\RecentPlays.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 2 ;
        recentPlaysButton.setBackground(new Color(0x4B829C));
        add(recentPlaysButton , gbc);

        playListsButton = new JButton();
        playListsButton.setText("PlayLists");
        playListsButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\PlayLists.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 3 ;
        playListsButton.setBackground(new Color(0x4B829C));
        add(playListsButton , gbc);

        favoritesButton = new JButton();
        favoritesButton.setText("Favorites");
        favoritesButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Favorites.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 4 ;
        favoritesButton.setBackground(new Color(0x4B829C));
        add(favoritesButton , gbc);

        addToLibraryButton = new JButton();
        addToLibraryButton.setText("Add to Library");
        addToLibraryButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Add.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 5 ;
        addToLibraryButton.setBackground(new Color(0x4B829C));
        add(addToLibraryButton , gbc);

        searchField = new JPanel();
        searchField.setLayout(new BorderLayout());
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("src\\Icons\\Search.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        JButton search = new JButton(searchIcon);
        search.setBackground(new Color(0x4B829C));
        JLabel searchLabel = new JLabel(searchIcon);
        searchLabel.setBackground(new Color(0x4B829C));
        searchField.add(search , BorderLayout.EAST);
        searchField.add(new JTextField() , BorderLayout.CENTER);
        gbc.gridy = 6 ;
        add(searchField , gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jpotify");
        frame.setLayout(new BorderLayout());
        frame.add(new LeftToolbar() , BorderLayout.NORTH);
        frame.setSize(400,450);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
