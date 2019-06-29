package GUI;

import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ShowSongsPanel extends JPanel implements ActionListener {

    private JPanel songsPanel;
    private JPanel northOptionPanel;
    private String optionPanelType;
    private JButton songs;
    private JButton albums;
    private JButton artists;
    private JButton addNewPlaylist;
    private JButton removePlaylist;
    private JButton addNewSong;
    private JButton removeSong;
    private JButton editSongs;
    private JLabel addNewSongLabel;
    private JLabel removeSongLabel;
    private JLabel songsFound;
    private JLabel favoritesLabel;
    private JLabel sharedPlaylistLabel ;
    private JLabel friendSharedPlaylistLabel ;
    private JLabel addToPlaylistLabel;
    private JLabel editPlaylistLabel;
    private JLabel changePositionLabel;
    private PlayerPanel playerPanel;
    private Playlist currentSelectedPlaylist;
    public static ArrayList<Song> songsToShow;
    private static HashMap<JButton, Song> getSongByButton;
    private static HashMap<JButton, Album> getAlbumByButton;
    private static HashMap<JButton, Artist> getArtistByButton;
    static HashMap<JButton, Playlist> getPlaylistByButton;
    private static GridBagConstraints gbc;

    /**
     * center panel for showing songs , albums , artists ,
     * playlists , favorites ...
     *
     * songs are played when clicked
     *
     * every button has a specified actionListener
     */

    ShowSongsPanel() {
        getSongByButton = new HashMap<>();
        getAlbumByButton = new HashMap<>();
        getArtistByButton = new HashMap<>();
        setLayout(new BorderLayout());
        optionPanelType = "null";

        songsPanel = new JPanel();
        songsPanel.setLayout(new GridBagLayout());
        songsPanel.setBackground(new Color(0xA4B8D3));
        add(songsPanel, BorderLayout.CENTER);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
    }

    void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    void setCurrentSelectedPlaylist(Playlist currentSelectedPlaylist) {
        this.currentSelectedPlaylist = currentSelectedPlaylist;
    }

    String getOptionPanelType() {
        return optionPanelType;
    }

    /**
     * updates center panel by an arraylist of songs
     * @param songsToUpdate songs to be shown in center panel
     * @param actionListener Listener according to button task
     */

    void updatePanelBySong(ArrayList<Song> songsToUpdate, ActionListener actionListener) {
        songsPanel.removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        getSongByButton = new HashMap<>();
        songsToShow = songsToUpdate;
        for (int i = songsToShow.size() - 1; i >= 0; i--) {
            Song song = songsToShow.get(i);
            JButton songAsButton = new JButton(new ImageIcon(song.getImage().getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            songAsButton.setText(song.getTitle());
            songAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            songAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            songAsButton.addActionListener(actionListener);
            getSongByButton.put(songAsButton, song);
            addComponentToSongsPanel(songAsButton);
        }
        add(songsPanel, BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    /**
     * updates panel by artists
     * all artists are shown
     * songs of the artist is shown when artist is clicked
     */

    private void updatePanelByArtist() {
        songsPanel.removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        getArtistByButton = new HashMap<>();
        for (int i = Library.artists.size() - 1; i >= 0; i--) {
            Artist artist = Library.artists.get(i);
            JButton artistAsButton = new JButton(new ImageIcon(artist.getSongs().get(0).getImage().getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            artistAsButton.addActionListener(this);
            artistAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            artistAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            artistAsButton.setText(artist.getName());
            getArtistByButton.put(artistAsButton, artist);
            addComponentToSongsPanel(artistAsButton);
        }
        add(songsPanel, BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    /**
     * updates panel by albums
     * all albums are shown
     * songs of the album is shown when album is clicked
     */

    private void updatePanelByAlbum() {
        songsPanel.removeAll();
        gbc.gridx = 0;
        gbc.gridy = 0;
        getAlbumByButton = new HashMap<>();
        for (int i = Library.albums.size() - 1; i >= 0; i--) {
            Album album = Library.albums.get(i);
            JButton albumAsButton = new JButton(new ImageIcon(album.getSongs().get(0).getImage().getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            albumAsButton.setText(album.getName());
            albumAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            albumAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            albumAsButton.addActionListener(this);
            getAlbumByButton.put(albumAsButton, album);
            addComponentToSongsPanel(albumAsButton);
        }
        add(songsPanel, BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    /**
     * updates panel by a specified playlist
     * all songs of the playlist is shown when the playlist is clicked
     */

    void updatePanelByPlaylist(ActionListener actionListener) {
        songsPanel.removeAll();
        gbc.gridx = gbc.gridy = 0;
        getPlaylistByButton = new HashMap<>();
        for (Playlist playlist : Library.playlists) {
            JButton playlistAsButton;
            int r = playlist.getSongs().size();
            if (r == 0)
                playlistAsButton = new JButton(new ImageIcon(new ImageIcon("src\\Icons\\Unknown.png").getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            else {
                r = new Random().nextInt(r);
                playlistAsButton = new JButton(new ImageIcon(playlist.getSongs().get(r).getImage().getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            }
            playlistAsButton.setText(playlist.getName());
//            playlistAsButton.setText(playlist.getName());
            playlistAsButton.addActionListener(actionListener);
            playlistAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            playlistAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            getPlaylistByButton.put(playlistAsButton, playlist);
            songsPanel.add(playlistAsButton, gbc);
            addComponentToSongsPanel(playlistAsButton);
        }
        add(songsPanel, BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }



    private void addComponentToSongsPanel(JButton button) {
        songsPanel.add(button, gbc);
        if (++gbc.gridx == 3) {
            gbc.gridx = 0;
            gbc.gridy++;
        }
    }

    /**
     * creates an option panel at the north of the center panel
     * @param type type of option panel
     */

    void createNorthPanel(String type) {
        if (optionPanelType.equals(type))
            return;
        if (northOptionPanel == null) {
            northOptionPanel = new JPanel();
            northOptionPanel.setBackground(new Color(0xE20B1E35, true));
            northOptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

            songs = new JButton("Songs");
            songs.setBackground(new Color(0x3E769C));
            songs.addActionListener(this);
            songs.setFocusPainted(false);

            albums = new JButton("Albums");
            albums.setBackground(new Color(0x3E769C));
            albums.addActionListener(this);
            albums.setFocusPainted(false);

            artists = new JButton("Artists");
            artists.setBackground(new Color(0x3E769C));
            artists.addActionListener(this);
            artists.setFocusPainted(false);

            addNewPlaylist = new JButton("Add new Playlist");
            addNewPlaylist.setBackground(new Color(0x3E769C));
            addNewPlaylist.addActionListener(this);
            addNewPlaylist.setFocusPainted(false);

            removePlaylist = new JButton("Remove Playlist");
            removePlaylist.setBackground(new Color(0x3E769C));
            removePlaylist.addActionListener(this);
            removePlaylist.setFocusPainted(false);

            addNewSong = new JButton("Add Song");
            addNewSong.setBackground(new Color(0x3E769C));
            addNewSong.addActionListener(this);
            addNewSong.setFocusPainted(false);

            removeSong = new JButton("Remove Song");
            removeSong.setBackground(new Color(0x3E769C));
            removeSong.addActionListener(this);
            removeSong.setFocusPainted(false);

            editSongs = new JButton("Edit Songs");
            editSongs.setBackground(new Color(0x3E769C));
            editSongs.addActionListener(this);
            editSongs.setFocusPainted(false);

            addNewSongLabel = new JLabel("Select songs you want to add to this playlist :");
            addNewSongLabel.setForeground(new Color(-1));

            removeSongLabel = new JLabel("Select songs you want to remove from this playlist :");
            removeSongLabel.setForeground(new Color(-1));

            favoritesLabel = new JLabel("Favorites         ");
            favoritesLabel.setForeground(new Color(-1));

            sharedPlaylistLabel = new JLabel("Shared Playlist         ");
            sharedPlaylistLabel.setForeground(new Color(-1));

            friendSharedPlaylistLabel = new JLabel();
            friendSharedPlaylistLabel.setForeground(new Color(-1));

            addToPlaylistLabel = new JLabel("Select Playlist to Add :");
            addToPlaylistLabel.setForeground(new Color(-1));

            editPlaylistLabel = new JLabel("Select song to change position :");
            editPlaylistLabel.setForeground(new Color(-1));

            changePositionLabel = new JLabel("Select position to replace :");
            changePositionLabel.setForeground(new Color(-1));

            songsFound = new JLabel();
            songsFound.setForeground(new Color(-1));

            friendSharedPlaylistLabel = new JLabel();
            friendSharedPlaylistLabel.setForeground(new Color(-1));
        }
        try {
            northOptionPanel.removeAll();
            northOptionPanel.repaint();
            northOptionPanel.revalidate();
        } catch (NullPointerException ignored) {
        }

        switch (type) {
            case "Library":
                northOptionPanel.add(songs);
                northOptionPanel.add(albums);
                northOptionPanel.add(artists);
                break;
            case "Playlists":
                northOptionPanel.add(addNewPlaylist);
                northOptionPanel.add(removePlaylist);
                break;
            case "Playlist":
                northOptionPanel.add(addNewSong);
                northOptionPanel.add(removeSong);
                northOptionPanel.add(editSongs);
                break;
            case "AddNewSong":
                northOptionPanel.add(addNewSongLabel);
                break;
            case "RemoveSong":
                northOptionPanel.add(removeSongLabel);
                break;
            case "EditSongs":
                northOptionPanel.add(editPlaylistLabel);
                break;
            case "ChangePosition":
                northOptionPanel.add(changePositionLabel);
                break;
            case "Favorites":
                northOptionPanel.add(favoritesLabel);
                northOptionPanel.add(addNewSong);
                northOptionPanel.add(removeSong);
                northOptionPanel.add(editSongs);
                break;
            case "SharedPlaylist" :
                northOptionPanel.add(sharedPlaylistLabel);
                northOptionPanel.add(addNewSong);
                northOptionPanel.add(removeSong);
                break;
            case "AddToPlaylist":
                northOptionPanel.add(addToPlaylistLabel);
                break;
            default:
                if(type.contains("Results")){
                    songsFound.setText(type);
                    northOptionPanel.add(songsFound);
                }
                else if(type.contains("Shared Playlist")){
                    friendSharedPlaylistLabel.setText(type);
                    northOptionPanel.add(friendSharedPlaylistLabel);
                }
        }
        northOptionPanel.repaint();
        northOptionPanel.revalidate();
        add(northOptionPanel, BorderLayout.NORTH);
        revalidate();
        optionPanelType = type;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        if (buttonPressed.equals(songs)) {
            updatePanelBySong(Library.allSongs, this);
        } else if (buttonPressed.equals(albums))
            updatePanelByAlbum();
        else if (buttonPressed.equals(artists))
            updatePanelByArtist();
        else if (buttonPressed.equals(addNewPlaylist)) {
            String newPlaylistName = JOptionPane.showInputDialog(null, "Enter Playlist Name", "New Playlist", JOptionPane.INFORMATION_MESSAGE);
            if (newPlaylistName == null) return ;
            if (newPlaylistName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Valid Name!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Library.findPlaylist(newPlaylistName) != null) {
                JOptionPane.showMessageDialog(null, "Playlist Already Exists!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Playlist newPlaylist = new Playlist(newPlaylistName);
            Library.playlists.add(newPlaylist);
            updatePanelByPlaylist(this);
            SaveData.savePlaylists();
            SaveData.savePlaylist(newPlaylist.getSongs() , "Playlists\\" + newPlaylistName);
        } else if (buttonPressed.equals(removePlaylist)) {
            String name = JOptionPane.showInputDialog(null, "Enter Playlist Name", "Remove Playlist", JOptionPane.INFORMATION_MESSAGE);
            if (name == null)
                return;
            Playlist playlist = Library.findPlaylist(name);
            if (playlist == null)
                JOptionPane.showMessageDialog(null, "Playlist Doesn't Exists!", "Remove Playlist", JOptionPane.ERROR_MESSAGE);
            else {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently remove playlist \"" + name + "\" ?");
                switch (choice) {
                    case JOptionPane.OK_OPTION:
                        Library.playlists.remove(playlist);
                        SaveData.savePlaylists();
                        SaveData.deletePlaylist(playlist);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                    case JOptionPane.NO_OPTION:
                        return;
                }
                JOptionPane.showMessageDialog(null, "Playlist Removed!", "Remove Playlist", JOptionPane.PLAIN_MESSAGE);
                updatePanelByPlaylist(this);
            }

        } else if (buttonPressed.equals(addNewSong)) {
            createNorthPanel("AddNewSong");
            ArrayList<Song> songsNotInPlaylist = new ArrayList<>();
            for (Song song : Library.allSongs) {
                if (!currentSelectedPlaylist.getSongs().contains(song))
                    songsNotInPlaylist.add(song);
            }
            songsToShow = songsNotInPlaylist;
            updatePanelBySong(songsNotInPlaylist, new AddListener());
        } else if (buttonPressed.equals(removeSong)) {
            createNorthPanel("RemoveSong");
            updatePanelBySong(currentSelectedPlaylist.getSongs(), new RemoveListener());
        } else if (buttonPressed.equals(editSongs)) {
            createNorthPanel("EditSongs");
            updatePanelBySong(currentSelectedPlaylist.getSongs(), new EditListener());
        } else if (getSongByButton.keySet().contains(buttonPressed)) {
            playerPanel.updatePanel(getSongByButton.get(buttonPressed));

            Library.allSongs.remove(PlayerPanel.nowPlayingSong);
            Library.allSongs.add(PlayerPanel.nowPlayingSong);

            Album album = Library.findAlbum(PlayerPanel.nowPlayingSong.getAlbum());
            Library.albums.remove(album);
            Library.albums.add(album);

            Artist artist = Library.findArtist(PlayerPanel.nowPlayingSong.getArtist());
            Library.artists.remove(artist);
            Library.artists.add(artist);

            if (songsToShow.equals(Library.allSongs))
                updatePanelBySong(Library.allSongs, this);
            PlayerPanel.songQueue = songsToShow;
            SaveData.saveLibrary();
        } else if (getAlbumByButton.keySet().contains(buttonPressed)) {
            updatePanelBySong(getAlbumByButton.get(buttonPressed).getSongs(), this);
        } else if (getArtistByButton.keySet().contains(buttonPressed))
            updatePanelBySong(getArtistByButton.get(buttonPressed).getSongs(), this);
        else if (getPlaylistByButton.keySet().contains(buttonPressed)) {
            createNorthPanel("Playlist");
            currentSelectedPlaylist = getPlaylistByButton.get(buttonPressed);
            updatePanelBySong(currentSelectedPlaylist.getSongs(), this);
        }
    }

    private class AddListener implements ActionListener {

        /**
         * listener for adding a song to a playlist
         */

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            Song selectedSong = getSongByButton.get(buttonPressed);
            currentSelectedPlaylist.addSong(selectedSong);
            songsToShow.remove(selectedSong);
            updatePanelBySong(songsToShow, this);
            if(!currentSelectedPlaylist.equals(Library.favorites) && !currentSelectedPlaylist.equals(Library.sharedPlaylist))
                SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , "Playlists\\" + currentSelectedPlaylist.getName());
            else
            SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , currentSelectedPlaylist.getName());
        }
    }

    private class RemoveListener implements ActionListener {

        /**
         * listener for adding a song to a playlist
         */

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            Song selectedSong = getSongByButton.get(buttonPressed);
            currentSelectedPlaylist.removeSong(selectedSong);
            updatePanelBySong(currentSelectedPlaylist.getSongs(), this);
            if(!currentSelectedPlaylist.equals(Library.favorites) && !currentSelectedPlaylist.equals(Library.sharedPlaylist))
                SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , "Playlists\\" + currentSelectedPlaylist.getName());
            else
                SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , currentSelectedPlaylist.getName());
        }
    }

    /**
     * Listener for selecting song of a playlist to change position
     */

    private class EditListener implements ActionListener {
        private Song songToChangePosition;

        @Override
        public void actionPerformed(ActionEvent e) {
            createNorthPanel("ChangePosition");
            JButton buttonPressed = (JButton) e.getSource();
            songToChangePosition = getSongByButton.get(buttonPressed);
            currentSelectedPlaylist.removeSong(songToChangePosition);
            updatePanelBySong(currentSelectedPlaylist.getSongs(), new ChangePositionListener());
        }

        /**
         * Listener for changing position of a selected song
         * Songs position will be before the selected song
         */

        private class ChangePositionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonPressed = (JButton) e.getSource();
                Song songToReplace = getSongByButton.get(buttonPressed);
                ArrayList<Song> newOrderedPlaylist = new ArrayList<>();
                for (Song song : currentSelectedPlaylist.getSongs()) {
                    newOrderedPlaylist.add(song);
                    if (song.equals(songToReplace))
                        newOrderedPlaylist.add(songToChangePosition);
                }
                currentSelectedPlaylist.setSongs(newOrderedPlaylist);
                createNorthPanel("Playlist");
                updatePanelBySong(currentSelectedPlaylist.getSongs(), ShowSongsPanel.this::actionPerformed);
                if(!currentSelectedPlaylist.equals(Library.favorites))
                    SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , "Playlists\\" + currentSelectedPlaylist.getName());
                else
                    SaveData.savePlaylist(currentSelectedPlaylist.getSongs() , currentSelectedPlaylist.getName());
            }
        }
    }
}
