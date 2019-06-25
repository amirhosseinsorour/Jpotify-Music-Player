package GUI;

import Logic.Library;
import Logic.Song;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JTextField searchTextField ;
    private JPanel searchField ;
    private ShowSongsPanel showSongsPanel ;

    public LeftToolbar(){
        setLayout(new GridBagLayout());
        setSize(100,200);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,30,5);
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.ipadx  = 150 ;
        setBackground(new Color(0xD6000821, true));

        searchField = new JPanel();
        searchField.setLayout(new BorderLayout());
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("src\\Icons\\Search.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
        searchButton = new JButton(searchIcon);
        searchButton.addActionListener(this);
        searchButton.setBackground(new Color(0x3E769C));
//        JLabel searchLabel = new JLabel(searchIcon);
//        searchLabel.setBackground(new Color(0x4B829C));
        searchField.add(searchButton , BorderLayout.EAST);
        searchTextField = new JTextField("Search Music");
        searchTextField.addFocusListener(new FocusListener() {
//            private boolean isFocused = true ;
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                source.setText("");
//                isFocused = true ;
//                source.removeActionListener(this);
            }
            @Override
            public void focusLost(FocusEvent e) {
                JTextField source = (JTextField) e.getComponent();
                if(source.getText().equals("")) {
                    source.setText("Search Music");
                }
//                isFocused = false;
            }
        });
        searchField.add(searchTextField , BorderLayout.CENTER);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        add(searchField , gbc);

        libraryButton = new JButton();
        libraryButton.addActionListener(this);
        libraryButton.setText("Library");
        libraryButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\MyLibrary.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 1;
        libraryButton.setBackground(new Color(0x3E769C));
        add(libraryButton , gbc) ;

        playListsButton = new JButton();
        playListsButton.addActionListener(this);
        playListsButton.setText("PlayLists");
        playListsButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\PlayLists.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 2 ;
        playListsButton.setBackground(new Color(0x3E769C));
        add(playListsButton , gbc);

        sharedPlaylistButton = new JButton();
        sharedPlaylistButton.addActionListener(this);
        sharedPlaylistButton.setText("Shared Playlist");
        sharedPlaylistButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\SharedPlaylist.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 3 ;
        sharedPlaylistButton.setBackground(new Color(0x3E769C));
        add(sharedPlaylistButton , gbc);

        favoritesButton = new JButton();
        favoritesButton.addActionListener(this);
        favoritesButton.setText("Favorites");
        favoritesButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Favorites.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 4 ;
        favoritesButton.setBackground(new Color(0x3E769C));
        add(favoritesButton , gbc);

        addToLibraryButton = new JButton();
        addToLibraryButton.addActionListener(this);
        addToLibraryButton.setText("Add to Library");
        addToLibraryButton.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Add.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        gbc.gridy = 5 ;
        addToLibraryButton.setBackground(new Color(0x3E769C));
        add(addToLibraryButton , gbc);

        JButton jpotifyLogo = new JButton();
//        jpotifyLogo.setEnabled(false);
        jpotifyLogo.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\JPotify.png").getImage().getScaledInstance(200,60,Image.SCALE_AREA_AVERAGING)));
        gbc.gridy = 6 ;

        jpotifyLogo.setBackground(new Color(0x3E769C));
        add(jpotifyLogo , gbc);
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
            jfc.setCurrentDirectory(new File("D:\\Music"));
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.addChoosableFileFilter(new FileNameExtensionFilter("mp3 Files" , "mp3"));
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File[] selectedFiles = jfc.getSelectedFiles();
                for (File file : selectedFiles) {
                    try {
                        Song newSong = new Song(file.getPath());
                        Library.addSong(newSong);
//                        System.out.println(newSong.getTitle());
//                        ShowSongsPanel.songsToShow.add(newSong);
//                        System.out.println(newSong.getTitle());
                    } catch (Exception ex){ex.printStackTrace();}
                }
//                ShowSongsPanel.songsToShow = Library.allSongs ;
                MainPanel.updateSongsPanel();
                showSongsPanel.createNorthPanel("Library");
                showSongsPanel.updatePanelBySong(Library.allSongs , showSongsPanel);
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
    }
}
