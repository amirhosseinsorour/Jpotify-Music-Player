package GUI;

import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    private PlayerPanel playerPanel ;
    private Playlist currentSelectedPlaylist ;
    public static ArrayList<Song> songsToShow ;
    public static HashMap<JButton , Song> getSongByButton ;
    public static HashMap<JButton , Album> getAlbumByButton ;
    public static HashMap<JButton , Artist> getArtistByButton ;
    public static HashMap<JButton , Playlist> getPlaylistByButton ;
    private static GridBagConstraints gbc ;

    public ShowSongsPanel() {
        getSongByButton = new HashMap<JButton, Song>();
        getAlbumByButton = new HashMap<JButton, Album>();
        getArtistByButton = new HashMap<JButton , Artist>();
        setLayout(new BorderLayout());
        setBackground(new Color(0x7EB4D3));
        optionPanelType = "null" ;

        songsPanel = new JPanel();
        songsPanel.setLayout(new GridBagLayout());
        songsPanel.setBackground(new Color(0x7EB4D3));
        add(songsPanel , BorderLayout.CENTER);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(20 ,20,20,20);
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public void updatePanelBySong(ArrayList<Song> songsToUpdate , ActionListener actionListener){
        songsPanel.removeAll();
//        songsPanel.revalidate();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getSongByButton = new HashMap<JButton, Song>();
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

    public void updatePanelByArtist(){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getArtistByButton = new HashMap<JButton, Artist>();
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

    public void updatePanelByAlbum(){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getAlbumByButton = new HashMap<JButton, Album>();
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

    public void updatePanelByPlaylist(){
        songsPanel.removeAll();
        gbc.gridx = gbc.gridy = 0 ;
        getPlaylistByButton = new HashMap<JButton, Playlist>();
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
            playlistAsButton.addActionListener(this);
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

    public void createNorthPanel(String type){
        if(optionPanelType.equals(type))
            return;
        if(northOptionPanel == null){
            northOptionPanel = new JPanel();
            northOptionPanel.setBackground(new Color(0x4C6A7D));
            northOptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

            songs = new JButton("Songs");
            songs.addActionListener(this);

            albums = new JButton("Albums");
            albums.addActionListener(this);

            artists = new JButton("Artists");
            artists.addActionListener(this);

            addNewPlaylist = new JButton("Add new Playlist");
            addNewPlaylist.addActionListener(this);

            removePlaylist = new JButton("Remove Playlist");
            removePlaylist.addActionListener(this);

            addNewSong = new JButton("Add Song");
            addNewSong.addActionListener(this);

            removeSong = new JButton("Remove Song");
            removeSong.addActionListener(this);

            editSongs = new JButton("Edit Songs");
            editSongs.addActionListener(this);

            addNewSongLabel = new JLabel("Select songs you want to add to this playlist :");
            removeSongLabel = new JLabel("Select songs you want to remove from this playlist :");
            songsFound = new JLabel();
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
        else if(type.contains("Results")){
            songsFound.setText(type);
            northOptionPanel.add(songsFound);
        }
        northOptionPanel.revalidate();
        add(northOptionPanel, BorderLayout.NORTH);
        revalidate();
        optionPanelType = type ;
    }

    public void removeNorthPanel(){
        remove(northOptionPanel);
        optionPanelType = "" ;
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
//            System.out.println(newPlaylistName);
            if(newPlaylistName == null) return;
            if(newPlaylistName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Valid Name!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(Library.findPlaylist(newPlaylistName) != null)
                JOptionPane.showMessageDialog(null, "Enter Valid Name!", "Create New Playlist", JOptionPane.ERROR_MESSAGE);
            Playlist newPlaylist = new Playlist(newPlaylistName);
            Library.playlists.add(newPlaylist);
            updatePanelByPlaylist();
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
                updatePanelByPlaylist();
            }
        }

        else if(buttonPressed.equals(addNewSong)){
            createNorthPanel("AddNewSong");
            ArrayList<Song> songsNotInPlaylist = new ArrayList<Song>();
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
            createNorthPanel("Library");
            updatePanelBySong(Library.allSongs , this);
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
//            buttonPressed.setVisible(false);
//            System.out.println(this.getClass().getName());
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
