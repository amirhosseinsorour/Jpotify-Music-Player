package GUI;

import Logic.Library;
import Logic.SaveData;
import Logic.Song;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class LeftToolbar extends JPanel implements ActionListener {

    private JButton searchButton ;
    private JButton libraryButton ;
    private JButton sharedPlaylistButton ;
    private JButton playListsButton ;
    private JButton favoritesButton ;
    private JButton addToLibraryButton ;
    private JButton jpotifyLogo ;
    private JTextField searchTextField ;
    private InfoPanel infoPanel ;
    private ShowSongsPanel showSongsPanel ;
    private GridBagConstraints gbc ;

    LeftToolbar(){
        setLayout(new GridBagLayout());
        setSize(100,200);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,30,5);
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.ipadx  = 150 ;
        setBackground(new Color(0xD6000821, true));
        infoPanel = new InfoPanel();

        JPanel searchField = new JPanel();
        searchField.setLayout(new BorderLayout());
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("src\\Icons\\Search.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        searchButton = new JButton(searchIcon);
        searchButton.addActionListener(this);
        searchButton.setBackground(new Color(0x3E769C));
        searchButton.setFocusPainted(false);
        searchField.add(searchButton , BorderLayout.EAST);
        searchTextField = new JTextField("Search Music");
        searchTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                if(source.getText().equals("")) {
                    source.setText("Search Music");
                }
            }
        });
        searchField.add(searchTextField , BorderLayout.CENTER);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        add(searchField , gbc);

        libraryButton = new JButton();
        initializeButton(libraryButton , "Library" , "src\\Icons\\MyLibrary.png");

        playListsButton = new JButton();
        initializeButton(playListsButton , "PlayLists" , "src\\Icons\\PlayLists.png");

        sharedPlaylistButton = new JButton();
        initializeButton(sharedPlaylistButton , "SharedPlaylist" , "src\\Icons\\SharedPlaylist.png");

        favoritesButton = new JButton();
        initializeButton(favoritesButton , "Favorites" , "src\\Icons\\Favorites.png");

        addToLibraryButton = new JButton();
        initializeButton(addToLibraryButton , "Add to Library" , "src\\Icons\\Add.png");

        jpotifyLogo = new JButton();
        jpotifyLogo.addActionListener(this);
        jpotifyLogo.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\JPotify.png").getImage().getScaledInstance(200,60,Image.SCALE_AREA_AVERAGING)));
        gbc.gridy ++ ;

        jpotifyLogo.setBackground(new Color(0x3E769C));
        add(jpotifyLogo , gbc);
    }

    private void initializeButton(JButton button , String buttonText , String imagePath){
        button.setText(buttonText);
        button.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40,40 , Image.SCALE_DEFAULT)));
        button.setBackground(new Color(0x3E769C));
        button.setFocusPainted(false);
        button.addActionListener(this);
        gbc.gridy++ ;
        add(button , gbc);
    }

    public void setShowSongsPanel(ShowSongsPanel showSongsPanel) {
        this.showSongsPanel = showSongsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addToLibraryButton)){
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Add new Song");
            jfc.setMultiSelectionEnabled(true);
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setCurrentDirectory(new File("D:\\Music")); // should be commented
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.addChoosableFileFilter(new FileNameExtensionFilter("mp3 Files" , "mp3"));
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = jfc.getSelectedFiles();
                for (File file : selectedFiles) {
                    try {
                        Song newSong = new Song(file.getPath());
                        Library.addSong(newSong);
                    } catch (Exception ex){ex.printStackTrace();}
                }
                MainPanel.updateSongsPanel();
                showSongsPanel.createNorthPanel("Library");
                showSongsPanel.updatePanelBySong(Library.allSongs , showSongsPanel);
                SaveData.saveLibrary();
            }
        }
        if(e.getSource().equals(libraryButton)) {
            MainPanel.updateSongsPanel();
            showSongsPanel.createNorthPanel("Library");
            showSongsPanel.updatePanelBySong(Library.allSongs , showSongsPanel);
        }
        if(e.getSource().equals(playListsButton)){
            MainPanel.updateSongsPanel();
            showSongsPanel.createNorthPanel("Playlists");
            try {
                showSongsPanel.updatePanelByPlaylist(showSongsPanel);
            }catch (NullPointerException ignored){}
        }
        if(e.getSource().equals(favoritesButton)){
            MainPanel.updateSongsPanel();
            showSongsPanel.createNorthPanel("Favorites");
            showSongsPanel.setCurrentSelectedPlaylist(Library.favorites);
            showSongsPanel.updatePanelBySong(Library.favorites.getSongs() , showSongsPanel);
        }
        if(e.getSource().equals(sharedPlaylistButton)){
            MainPanel.updateSongsPanel();
            showSongsPanel.createNorthPanel("SharedPlaylist");
            showSongsPanel.setCurrentSelectedPlaylist(Library.sharedPlaylist);
            showSongsPanel.updatePanelBySong(Library.sharedPlaylist.getSongs() , showSongsPanel);
        }
        if(e.getSource().equals(searchButton)){
            String name = searchTextField.getText();
            ArrayList<Song> foundSongs = new ArrayList<Song>();
            for(Song song : Library.allSongs){
                if(song.getTitle().contains(name) || song.getAlbum().contains(name) || song.getArtist().contains(name)) {
                    foundSongs.add(song);
                }
            }
            showSongsPanel.createNorthPanel("Results for \"" + name + "\" :");
            showSongsPanel.updatePanelBySong(foundSongs , showSongsPanel);
            searchTextField.setText("Search Music");
        }
        if(e.getSource().equals(jpotifyLogo)){
            infoPanel.setVisible(true);
        }
    }
}
