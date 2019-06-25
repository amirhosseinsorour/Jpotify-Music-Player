package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Logic.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerPanel extends JPanel implements ActionListener , ChangeListener {

    private static Song song ;
    private SongTimer timer ;
    private PausablePlayer player ;
    private boolean isPlaying = false ;
    private JLabel songPicLabel ;
    private JLabel songTitle ;
    private JLabel songArtist;
    private JLabel songAlbum ;
    private JButton shuffle ;
    private JButton previous ;
    private JButton playPause ;
    private JButton next ;
    private JButton repeat ;
    private JButton mute ;
    private JButton addToPlaylist ;
    private JButton addToFavorites ;
    private JSlider volume ;
    private JSlider songSlider ;
    private JPanel songSliderPanel ;
    private JLabel songTotalLengthLabel ;
    private JLabel songCurrentTimePassed ;

    public PlayerPanel() throws Exception{
        setLayout(new BorderLayout());
        player = new PausablePlayer(new FileInputStream(song.getAddress()));

        JPanel songInfoPanel = new JPanel();
        songInfoPanel.setBackground(new Color(0xE20B1E35));
        songInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(10 ,10,10,10);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        songPicLabel = new JLabel(new ImageIcon(song.getImage().getImage().getScaledInstance(230,230,Image.SCALE_DEFAULT)));
        songInfoPanel.add(songPicLabel , gbc);

        JPanel songInfoInnerPanel = new JPanel();
        songInfoInnerPanel.setBackground(new Color(0x3E769C));
        songInfoInnerPanel.setLayout(new GridBagLayout());
//        songInfoInnerPanel.setBackground(new Color(0x4B829C));

        songTitle = new JLabel(song.getTitle());
        gbc.insets = new Insets(0 ,10,0,10);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        songTitle.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
//        JPanel songTitlePanel = new JPanel();
//        songTitlePanel.add(songTitle);
//        songTitlePanel.setBackground(new Color(0x4B829C));
        songInfoInnerPanel.add(songTitle , gbc);

        songArtist = new JLabel(song.getArtist());
        gbc.insets = new Insets(70 ,10,70,10);
        gbc.gridy = 1 ;
//        songArtist.setForeground(new Color(0xFF211D));
        songArtist.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
        songInfoInnerPanel.add(songArtist , gbc);

        songAlbum = new JLabel(song.getAlbum());
        gbc.insets = new Insets(0 ,10,0,10);
        gbc.gridy = 2 ;
        songAlbum.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
        songInfoInnerPanel.add(songAlbum , gbc);

        gbc.gridx = 1 ;     gbc.gridy = 0 ;
        songInfoPanel.add(songInfoInnerPanel , gbc);


        add(songInfoPanel , BorderLayout.WEST);

        JPanel playerArea = new JPanel();
        playerArea.setLayout(new GridBagLayout());

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(0xE20B1E35));
        buttons.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5,5,5,5);
        initializeButtons();
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        buttons.add(shuffle , gbc);
        gbc.gridx = 1 ;
        buttons.add(previous , gbc);
        gbc.gridx = 2 ;
        buttons.add(playPause , gbc);
        gbc.gridx = 3 ;
        buttons.add(playPause , gbc);
        gbc.gridx = 4 ;
        buttons.add(next , gbc);
        gbc.gridx = 5 ;
        buttons.add(repeat);

        gbc.insets = new Insets(5,10,20,10);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        gbc.weightx = 2 ;
        playerArea.add(buttons , gbc);

        songSliderPanel = new JPanel();
        songSliderPanel.setBackground(new Color(0xE20B1E35));
        songSliderPanel.setLayout(new BorderLayout());

        songTotalLengthLabel = new JLabel(song.getSongLength());
        songTotalLengthLabel.setForeground(new Color(-1));
        songTotalLengthLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
        songSliderPanel.add(songTotalLengthLabel , BorderLayout.EAST);

        songCurrentTimePassed = new JLabel("0:00");
        songCurrentTimePassed.setForeground(new Color(-1));
        songCurrentTimePassed.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
        songSliderPanel.add(songCurrentTimePassed , BorderLayout.WEST);

        songSlider = new JSlider();
        songSlider.setBackground(new Color(0xE20B1E35));
        songSlider.setMinimum(0);
        songSlider.setValue(0);
        songSliderPanel.add(songSlider , BorderLayout.CENTER);
        gbc.gridy = 1 ;
        playerArea.add(songSliderPanel , gbc);

        playerArea.setBackground(new Color(0x3E769C));

        add(playerArea , BorderLayout.CENTER);

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridBagLayout());
        optionPanel.setBackground(new Color(0xE20B1E35));

        JPanel volumePanel = new JPanel();
        volumePanel.setBackground(new Color(0x3E769C));
        volumePanel.setLayout(new BorderLayout());

        JLabel volumeIcon = new JLabel();
        volumeIcon.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Volume.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT)));
        volumePanel.add(volumeIcon , BorderLayout.WEST);

        volume = new JSlider(0,100,50);
        volume.setBackground(new Color(0x3E769C));
        volume.setMinorTickSpacing(10);
        volume.setPaintTicks(true);
        volume.addChangeListener(this);
        volumePanel.add(volume , BorderLayout.CENTER);

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
//        gbc.gridwidth = 3 ;
        optionPanel.add(volumePanel , gbc);

        JPanel rightButtonsPanel = new JPanel();
        rightButtonsPanel.setBackground(new Color(0xE20B1E35));
        rightButtonsPanel.setLayout(new GridBagLayout());
        rightButtonsPanel.add(mute , gbc);
//        gbc.gridwidth = 1 ;
        gbc.gridy = 1 ;
        rightButtonsPanel.add(addToPlaylist , gbc);
        gbc.gridy = 2 ;
        rightButtonsPanel.add(addToFavorites , gbc);

        gbc.gridx = 0 ;     gbc.gridy = 1 ;
        optionPanel.add(rightButtonsPanel , gbc);

        add(optionPanel , BorderLayout.EAST);
    }

    public static void setSong(Song newSong) {
        song = newSong ;
    }

    public void updatePanel(Song newSong){
        try {
            setSong(newSong);
            isPlaying = false ;
            songCurrentTimePassed.setText("0:00");
            try {
                timer.setSongStatus(isPlaying);
            }catch (NullPointerException ignored){}
            timer = new SongTimer(songSlider , songCurrentTimePassed);
            timer.setTask();
            timer.setMaxTime((int)song.getLengthInSeconds());
            timer.setSongStatus(isPlaying);

            songPicLabel.setIcon(new ImageIcon(song.getImage().getImage().getScaledInstance(230, 230, Image.SCALE_DEFAULT)));
            songTitle.setText(song.getTitle());
            songArtist.setText(song.getArtist());
            songAlbum.setText(song.getAlbum());
            player.close();
            player = new PausablePlayer(new FileInputStream(song.getAddress()));

            Library.allSongs.remove(song);
            Library.allSongs.add(song);

            Album album = Library.findAlbum(song.getAlbum());
            Library.albums.remove(album);
            Library.albums.add(album);

            Artist artist = Library.findArtist(song.getArtist());
            Library.artists.remove(artist);
            Library.artists.add(artist);

            songSlider.setMaximum((int) song.getLengthInSeconds());
            songSlider.setValue(0);
            songTotalLengthLabel.setText(song.getSongLength());
        }catch (JavaLayerException | NullPointerException | IOException e){e.printStackTrace();}
    }

    private void initializeButtons(){
        shuffle = new JButton();
        shuffle.addActionListener(this);
        shuffle.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Shuffle.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        shuffle.setBackground(new Color(0x0C6C9B));

        previous = new JButton();
        previous.addActionListener(this);
        previous.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Previous.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        previous.setBackground(new Color(0x0C6C9B));

        playPause = new JButton();
        playPause.addActionListener(this);
        playPause.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\PlayPause.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        playPause.setBackground(new Color(0x0C6C9B));


        next = new JButton();
        next.addActionListener(this);
        next.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Next.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        next.setBackground(new Color(0x0C6C9B));

        repeat = new JButton();
        repeat.addActionListener(this);
        repeat.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Repeat.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        repeat.setBackground(new Color(0x0C6C9B));

        mute = new JButton();
        mute.addActionListener(this);
        mute.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\Mute.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        mute.setBackground(new Color(0x3E769C));
        mute.setText("Mute");

        addToPlaylist = new JButton();
        addToPlaylist.addActionListener(this);
        addToPlaylist.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\AddToPlaylist.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        addToPlaylist.setBackground(new Color(0x3E769C));
        addToPlaylist.setText("Add To Playlist");

        addToFavorites = new JButton();
        addToFavorites.addActionListener(this);
        addToFavorites.setIcon(new ImageIcon(new ImageIcon("src\\Icons\\AddToFavorites.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        addToFavorites.setBackground(new Color(0x3E769C));
        addToFavorites.setText("Add To Favorites");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(playPause)){
            if(timer == null) {
                timer = new SongTimer(songSlider , songCurrentTimePassed);
                timer.setTask();
                timer.setMaxTime((int)song.getLengthInSeconds());
                timer.setSongStatus(isPlaying);
            }
            try {
                if(!isPlaying) {
                    player.play();
                    isPlaying = true ;
                    timer.setSongStatus(isPlaying);
                    timer.start();
                }
                else {
                    player.pause();
                    isPlaying = false ;
                    timer.setSongStatus(isPlaying);
                }
            }catch (Exception ignored){}
        }
//        if(e.getSource().equals(shuffle))
//            player.pause();
//        if(e.getSource().equals(repeat))
//            player.resume();

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
