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

    private JPanel songsPanel ;
    private JPanel northOptionPanel ;
    private String optionPanelType ;
    private JButton songs ;
    private JButton albums ;
    private JButton artists ;
    private JButton addNewPlaylist ;
    private JButton removePlaylist ;
    private JButton addNewSong ;
    private JButton removeSong ;
    private JButton editSongs ;
    private JLabel addNewSongLabel ;
    private JLabel removeSongLabel ;
    private JLabel songsFound ;
    private JLabel favotitesLabel ;
    private JLabel addToPlaylistLabel ;
    private PlayerPanel playerPanel ;
    private Playlist currentSelectedPlaylist ;
    public static ArrayList<Song> songsToShow ;
    private static HashMap<JButton , Song> getSongByButton ;
    private static HashMap<JButton , Album> getAlbumByButton ;
    private static HashMap<JButton , Artist> getArtistByButton ;
    static HashMap<JButton , Playlist> getPlaylistByButton ;
    private static GridBagConstraints gbc ;

    ShowSongsPanel() {
        getSongByButton = new HashMap<>();
        getAlbumByButton = new HashMap<>();
        getArtistByButton = new HashMap<>();
        setLayout(new BorderLayout());
        optionPanelType = "null" ;

        songsPanel = new JPanel();
        songsPanel.setLayout(new GridBagLayout());
        songsPanel.setBackground(new Color(0xA4B8D3));
        add(songsPanel , BorderLayout.CENTER);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(20 ,20,20,20);
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

    void updatePanelBySong(ArrayList<Song> songsToUpdate , ActionListener actionListener){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getSongByButton = new HashMap<>();
        songsToShow = songsToUpdate;
        for(int i  = songsToShow.size()-1 ; i >=0 ; i--){
            Song song = songsToShow.get(i);
            JButton songAsButton = new JButton(new ImageIcon(song.getImage().getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            songAsButton.setText(song.getTitle());
            songAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            songAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            songAsButton.addActionListener(actionListener);
            getSongByButton.put(songAsButton , song);
            addComponentToSongsPanel(songAsButton);
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    private void updatePanelByArtist(){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getArtistByButton = new HashMap<>();
        for(int i = Library.artists.size()-1 ; i >= 0 ; i--){
            Artist artist = Library.artists.get(i);
            JButton artistAsButton = new JButton(new ImageIcon(artist.getSongs().get(0).getImage().getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            artistAsButton.addActionListener(this);
            artistAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            artistAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            artistAsButton.setText(artist.getName());
            getArtistByButton.put(artistAsButton , artist);
            addComponentToSongsPanel(artistAsButton);
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    private void updatePanelByAlbum(){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getAlbumByButton = new HashMap<>();
        for(int i = Library.albums.size()-1 ; i >= 0 ; i--){
            Album album = Library.albums.get(i);
            JButton albumAsButton = new JButton(new ImageIcon(album.getSongs().get(0).getImage().getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            albumAsButton.setText(album.getName());
            albumAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            albumAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            albumAsButton.addActionListener(this);
            getAlbumByButton.put(albumAsButton , album);
            addComponentToSongsPanel(albumAsButton);
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    void updatePanelByPlaylist(ActionListener actionListener){
        songsPanel.removeAll();
        gbc.gridx = gbc.gridy = 0 ;
        getPlaylistByButton = new HashMap<>();
        for(Playlist playlist : Library.playlists){
            JButton playlistAsButton ;
            int r = playlist.getSongs().size();
            if(r == 0)
                playlistAsButton = new JButton(new ImageIcon(new ImageIcon("src\\Icons\\Unknown.png").getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            else {
                r = new Random().nextInt(r) ;
                playlistAsButton = new JButton(new ImageIcon(playlist.getSongs().get(r).getImage().getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
            }
            playlistAsButton.setText(playlist.getName());
            playlistAsButton.setText(playlist.getName());
            playlistAsButton.addActionListener(actionListener);
            playlistAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            playlistAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            getPlaylistByButton.put(playlistAsButton , playlist);
            songsPanel.add(playlistAsButton , gbc);
            addComponentToSongsPanel(playlistAsButton);
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.repaint();
        songsPanel.revalidate();
    }

    private void addComponentToSongsPanel(JButton button){
        songsPanel.add(button , gbc);
        if(++gbc.gridx == 3){
            gbc.gridx = 0 ;
            gbc.gridy ++ ;
        }
    }

    void createNorthPanel(String type){
        if(optionPanelType.equals(type))
            return;
        if(northOptionPanel == null){
            northOptionPanel = new JPanel();
            northOptionPanel.setBackground(new Color(0xE20B1E35, true));
            northOptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

            songs = new JButton("Songs");
            songs.setBackground(new Color(0x3E769C));
            songs.addActionListener(this);

            albums = new JButton("Albums");
            albums.setBackground(new Color(0x3E769C));
            albums.addActionListener(this);

            artists = new JButton("Artists");
            artists.setBackground(new Color(0x3E769C));
            artists.addActionListener(this);

            addNewPlaylist = new JButton("Add new Playlist");
            addNewPlaylist.setBackground(new Color(0x3E769C));
            addNewPlaylist.addActionListener(this);

            removePlaylist = new JButton("Remove Playlist");
            removePlaylist.setBackground(new Color(0x3E769C));
            removePlaylist.addActionListener(this);

            addNewSong = new JButton("Add Song");
            addNewSong.setBackground(new Color(0x3E769C));
            addNewSong.addActionListener(this);

            removeSong = new JButton("Remove Song");
            removeSong.setBackground(new Color(0x3E769C));
            removeSong.addActionListener(this);

            editSongs = new JButton("Edit Songs");
            editSongs.setBackground(new Color(0x3E769C));
            editSongs.addActionListener(this);

            addNewSongLabel = new JLabel("Select songs you want to add to this playlist :");
            addNewSongLabel.setForeground(new Color(-1));

            removeSongLabel = new JLabel("Select songs you want to remove from this playlist :");
            removeSongLabel.setForeground(new Color(-1));

            favotitesLabel = new JLabel("Favorites         ");
            favotitesLabel.setForeground(new Color(-1));

            addToPlaylistLabel = new JLabel("Select Playlist to Add :");
            addToPlaylistLabel.setForeground(new Color(-1));

            songsFound = new JLabel();
            songsFound.setForeground(new Color(-1));
        }
        try {
            northOptionPanel.removeAll();
            northOptionPanel.repaint();
            northOptionPanel.revalidate();
        }catch (NullPointerException ignored){}

        if (type.equals("Library")) {
            northOptionPanel.add(songs);
            northOptionPanel.add(albums);
            northOptionPanel.add(artists);
        }
        else if(type.equals("Playlists")){
            northOptionPanel.add(addNewPlaylist);
            northOptionPanel.add(removePlaylist);
        }
        else if(type.equals("Playlist")){
            northOptionPanel.add(addNewSong);
            northOptionPanel.add(removeSong);
            northOptionPanel.add(editSongs);
        }
        else if(type.equals("AddNewSong")){
            northOptionPanel.add(addNewSongLabel);
        }
        else if(type.equals("RemoveSong")){
            northOptionPanel.add(removeSongLabel);
        }
        else if(type.equals("Favorites")){
            northOptionPanel.add(favotitesLabel);
            northOptionPanel.add(addNewSong);
            northOptionPanel.add(removeSong);
            northOptionPanel.add(editSongs);
        }
        else if(type.equals("AddToPlaylist")){
            northOptionPanel.add(addToPlaylistLabel);
        }
        else if(type.contains("Results")){
            songsFound.setText(type);
            northOptionPanel.add(songsFound);
        }
        northOptionPanel.repaint();
        northOptionPanel.revalidate();
        add(northOptionPanel, BorderLayout.NORTH);
        revalidate();
        optionPanelType = type ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        if(buttonPressed.equals(songs)){
            updatePanelBySong(Library.allSongs , this);
        }
        else if(buttonPressed.equals(albums))
            updatePanelByAlbum();
        else if(buttonPressed.equals(artists))
            updatePanelByArtist();
        else if(buttonPressed.equals(addNewPlaylist)){
            String newPlaylistName = JOptionPane.showInputDialog(null ,"Enter Playlist Name" ,"New Playlist",JOptionPane.INFORMATION_MESSAGE );
            if(newPlaylistName == null) return;
            if(newPlaylistName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Valid Name!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(Library.findPlaylist(newPlaylistName) != null) {
                JOptionPane.showMessageDialog(null, "Playlist Already Exists!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Playlist newPlaylist = new Playlist(newPlaylistName);
            Library.playlists.add(newPlaylist);
            updatePanelByPlaylist(this);
        }
        else if(buttonPressed.equals(removePlaylist)){
            String name = JOptionPane.showInputDialog(null ,"Enter Playlist Name" ,"Remove Playlist",JOptionPane.INFORMATION_MESSAGE );
            if(name == null)
                return;
            Playlist playlist = Library.findPlaylist(name);
            if(playlist == null)
                JOptionPane.showMessageDialog(null, "Playlist Doesn't Exists!" , "Remove Playlist" , JOptionPane.ERROR_MESSAGE);
            else{
                int choice = JOptionPane.showConfirmDialog(null , "Are you sure you want to permanently remove playlist \"" + name + "\" ?");
                switch (choice){
                    case JOptionPane.OK_OPTION :
                        Library.playlists.remove(playlist);
                        break;
                    case JOptionPane.CANCEL_OPTION :
                    case JOptionPane.CLOSED_OPTION :
                    case JOptionPane.NO_OPTION :
                        return;
                }
                JOptionPane.showMessageDialog(null , "Playlist Removed!" , "Remove Playlist" , JOptionPane.PLAIN_MESSAGE);
                updatePanelByPlaylist(this);
            }
        }

        else if(buttonPressed.equals(addNewSong)){
            createNorthPanel("AddNewSong");
            ArrayList<Song> songsNotInPlaylist = new ArrayList<>();
            for(Song song : Library.allSongs){
                if(!currentSelectedPlaylist.getSongs().contains(song))
                    songsNotInPlaylist.add(song);
            }
            songsToShow = songsNotInPlaylist ;
            updatePanelBySong(songsNotInPlaylist , new AddListener());
        }

        else if(buttonPressed.equals(removeSong)){
            createNorthPanel("RemoveSong");
            updatePanelBySong(currentSelectedPlaylist.getSongs() , new RemoveListener());
        }

        else if(getSongByButton.keySet().contains(buttonPressed)) {
            playerPanel.updatePanel(getSongByButton.get(buttonPressed));

            Library.allSongs.remove(PlayerPanel.nowPlayingSong);
            Library.allSongs.add(PlayerPanel.nowPlayingSong);

            Album album = Library.findAlbum(PlayerPanel.nowPlayingSong.getAlbum());
            Library.albums.remove(album);
            Library.albums.add(album);

            Artist artist = Library.findArtist(PlayerPanel.nowPlayingSong.getArtist());
            Library.artists.remove(artist);
            Library.artists.add(artist);

            if(songsToShow.equals(Library.allSongs))
                updatePanelBySong(Library.allSongs , this);
            PlayerPanel.songQueue = songsToShow ;
        }

        else if(getAlbumByButton.keySet().contains(buttonPressed)){
            updatePanelBySong(getAlbumByButton.get(buttonPressed).getSongs() , this);
        }
        else if(getArtistByButton.keySet().contains(buttonPressed))
            updatePanelBySong(getArtistByButton.get(buttonPressed).getSongs() , this);
        else if(getPlaylistByButton.keySet().contains(buttonPressed)) {
            createNorthPanel("Playlist");
            currentSelectedPlaylist = getPlaylistByButton.get(buttonPressed);
            updatePanelBySong(currentSelectedPlaylist.getSongs() , this);
            System.out.println(currentSelectedPlaylist.getName());
        }
    }
    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            Song selectedSong = getSongByButton.get(buttonPressed);
            currentSelectedPlaylist.addSong(selectedSong);
            songsToShow.remove(selectedSong);
            updatePanelBySong(songsToShow , this);
        }
    }

    private class RemoveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            Song selectedSong = getSongByButton.get(buttonPressed);
            currentSelectedPlaylist.removeSong(selectedSong);
            updatePanelBySong(currentSelectedPlaylist.getSongs() , this);
        }
    }
}
