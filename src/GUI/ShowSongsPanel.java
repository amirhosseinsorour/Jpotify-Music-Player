package GUI;

import Logic.Album;
import Logic.Library;
import Logic.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ShowSongsPanel extends JPanel implements ActionListener {

    private JPanel songsPanel ;
    private JButton songs ;
    private JButton albums ;
    private JButton artists ;
    private PlayerPanel playerPanel ;
    public static ArrayList<Song> songsToShow ;
    public static HashMap<JButton , Song> getSongByButton ;
    public static HashMap<JButton , Album> getAlbumByButton ;
    private static GridBagConstraints gbc ;

    public ShowSongsPanel() {
        getSongByButton = new HashMap<JButton, Song>();
        getAlbumByButton = new HashMap<JButton, Album>();
        setLayout(new BorderLayout());
        setBackground(new Color(0x7EB4D3));

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

    public void updatePanelbySong(ArrayList<Song> songsToUpdate){
        songsPanel.removeAll();
        songsPanel.revalidate();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getSongByButton = new HashMap<JButton, Song>();
        songsToShow = songsToUpdate;
        for(int i  = songsToShow.size()-1 ; i >=0 ; i--){
            Song song = songsToShow.get(i);
            JButton songAsButton = new JButton(new ImageIcon(new ImageIcon(song.getImage()).getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            songAsButton.setText(song.getTitle());
            songAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            songAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            songAsButton.addActionListener(this);
            getSongByButton.put(songAsButton , song);
            songsPanel.add(songAsButton , gbc);
            gbc.gridx ++ ;
            if(gbc.gridx == 3){
                gbc.gridx = 0 ;
                gbc.gridy ++ ;
            }
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.revalidate();
    }

    public void updatePanelByAlbum(){
        songsPanel.removeAll();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        getAlbumByButton = new HashMap<JButton, Album>();
        for(int i = Library.albums.size()-1 ; i >= 0 ; i--){
            Album album = Library.albums.get(i);
            JButton albumAsButton = new JButton(new ImageIcon(new ImageIcon(album.getSongs().get(0).getImage()).getImage().getScaledInstance(180,180,Image.SCALE_DEFAULT)));
            albumAsButton.setText(album.getName());
            albumAsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            albumAsButton.setHorizontalTextPosition(SwingConstants.CENTER);
            albumAsButton.addActionListener(this);
            getAlbumByButton.put(albumAsButton , album);
            songsPanel.add(albumAsButton , gbc);
            gbc.gridx ++ ;
            if(gbc.gridx >= 3){
                gbc.gridx = 0 ;
                gbc.gridy ++ ;
            }
        }
        add(songsPanel , BorderLayout.CENTER);
        songsPanel.revalidate();

    }

    public void createNorthPanel(String type){
        JPanel northOptionPanel = new JPanel();
        northOptionPanel.setBackground(new Color(0x4C6A7D));
        northOptionPanel.setLayout(new FlowLayout(3 ,5,5));
//        GridBagConstraints ngbc = new GridBagConstraints();

        if(type.equals("Library")){
//            ngbc.gridx = 0 ;
//            ngbc.gridy = 0 ;
//            ngbc.fill = GridBagConstraints.HORIZONTAL ;
//            ngbc.insets = new Insets(10,10,10,10);
            songs = new JButton("Songs");
            songs.setSize(50 , 10);
            songs.addActionListener(this);
            northOptionPanel.add(songs);

//            ngbc.gridx = 1 ;
            albums = new JButton("Albums");
            albums.addActionListener(this);
            northOptionPanel.add(albums);

//            ngbc.gridx = 2 ;
            artists = new JButton("Artists");
            artists.addActionListener(this);
            northOptionPanel.add(artists);

            add(northOptionPanel , BorderLayout.NORTH);
            revalidate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        if(buttonPressed.equals(songs)){
            updatePanelbySong(Library.allSongs);
        }
        else if(buttonPressed.equals(albums))
            updatePanelByAlbum();

        else if(getSongByButton.keySet().contains(buttonPressed)) {
            playerPanel.updatePanel(getSongByButton.get(buttonPressed));
            updatePanelbySong(Library.allSongs);
        }

        else if(getAlbumByButton.keySet().contains(buttonPressed)){
//            System.out.println(getAlbumByButton.get(buttonPressed));
            updatePanelbySong(getAlbumByButton.get(buttonPressed).getSongs());
        }

    }
}
