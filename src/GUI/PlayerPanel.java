package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Logic.*;
import javazoom.jl.decoder.JavaLayerException;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerPanel extends JPanel implements ActionListener , ChangeListener {

    public static Song nowPlayingSong ;
    public static ArrayList<Song> songQueue ;
    private SongTimer timer ;
    private PausablePlayer player ;
    private boolean isPlaying = false ;
    private boolean isRepeat = false ;
    private boolean isShuffle = false ;
    private boolean isMute = false ;
    private float volumeValue ;
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
    private ShowSongsPanel showSongsPanel ;
    private static final int NEXT_SONG = 1 ;
    private static final int PREVIOUS_SONG = -1 ;
    private static final int REPEAT_SONG = 0 ;
    private static final int SHUFFLE_SONG = 2 ;

    public PlayerPanel() throws Exception{
        setLayout(new BorderLayout());
        player = new PausablePlayer(nowPlayingSong , 0);

        JPanel songInfoPanel = new JPanel();
        songInfoPanel.setBackground(new Color(0xE20B1E35));
        songInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.insets = new Insets(10 ,10,10,10);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        songPicLabel = new JLabel(new ImageIcon(nowPlayingSong.getImage().getImage().getScaledInstance(230,230,Image.SCALE_DEFAULT)));
        songInfoPanel.add(songPicLabel , gbc);

        JPanel songInfoInnerPanel = new JPanel();
        songInfoInnerPanel.setBackground(new Color(0x3E769C));
        songInfoInnerPanel.setLayout(new GridBagLayout());
//        songInfoInnerPanel.setBackground(new Color(0x4B829C));

        songTitle = new JLabel(nowPlayingSong.getTitle());
        gbc.insets = new Insets(0 ,10,0,10);
        gbc.gridx = 0 ;     gbc.gridy = 0 ;
        songTitle.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
//        JPanel songTitlePanel = new JPanel();
//        songTitlePanel.add(songTitle);
//        songTitlePanel.setBackground(new Color(0x4B829C));
        songInfoInnerPanel.add(songTitle , gbc);

        songArtist = new JLabel(nowPlayingSong.getArtist());
        gbc.insets = new Insets(70 ,10,70,10);
        gbc.gridy = 1 ;
//        songArtist.setForeground(new Color(0xFF211D));
        songArtist.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));
        songInfoInnerPanel.add(songArtist , gbc);

        songAlbum = new JLabel(nowPlayingSong.getAlbum());
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

        songTotalLengthLabel = new JLabel(nowPlayingSong.getSongLength());
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
        songSlider.setMaximum((int)nowPlayingSong.getLengthInSeconds());
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

        volume = new JSlider(-40,40,0);
        volume.setBackground(new Color(0x3E769C));
        volume.setMinorTickSpacing(8);
        volumeValue = 0 ;
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
        nowPlayingSong = newSong ;
    }

    public void setShowSongsPanel(ShowSongsPanel showSongsPanel) {
        this.showSongsPanel = showSongsPanel;
    }

    public void updatePanel(Song newSong){
        try {
            if(Library.favorites.getSongs().contains(newSong))
                addToFavorites.setBackground(new Color(0x344C68));
            else
                addToFavorites.setBackground(new Color(0x3E769C));
            isPlaying = false ;
            setSong(newSong);
            try {
                timer.setSongStatus(isPlaying);
            }catch (NullPointerException ignored){}
            timer = new SongTimer(songSlider , songCurrentTimePassed);
            timer.setTask();
            timer.setMaxTime((int)nowPlayingSong.getLengthInSeconds());
            timer.setSongStatus(isPlaying);

            songPicLabel.setIcon(new ImageIcon(nowPlayingSong.getImage().getImage().getScaledInstance(230, 230, Image.SCALE_DEFAULT)));
            songTitle.setText(nowPlayingSong.getTitle());
            songArtist.setText(nowPlayingSong.getArtist());
            songAlbum.setText(nowPlayingSong.getAlbum());
            player.close();
            player = new PausablePlayer(nowPlayingSong , 0);

            songSlider.setMaximum((int) nowPlayingSong.getLengthInSeconds());
            songSlider.setValue(0);
            songTotalLengthLabel.setText(nowPlayingSong.getSongLength());
            songCurrentTimePassed.setText("0:00");

            timer.start();
            player.play();
            if(!isMute) {
                Thread.sleep(10);
                player.setVolume(volumeValue);
            }
            isPlaying = true ;
            timer.setSongStatus(true);


            SaveData.saveLastSong(newSong);
        }catch (JavaLayerException | NullPointerException | IOException | InterruptedException e){e.printStackTrace();}
    }

    private void updatePanelByDefault(){
        Song nextSong ;
        if(isRepeat)
            nextSong = findSong(REPEAT_SONG);
        else if(isShuffle)
            nextSong = findSong(SHUFFLE_SONG);
        else
        nextSong = findSong(NEXT_SONG);

        if(nextSong != null)
            updatePanel(nextSong);
    }

    private void initializeButton(JButton newButton , String imagePath){
        newButton.addActionListener(this);
        newButton.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
        newButton.setBackground(new Color(0x0C6C9B));
        newButton.setFocusPainted(false);
    }

    private void initializeButtons(){

        shuffle = new JButton();
        previous = new JButton();
        playPause = new JButton();
        next = new JButton();
        repeat = new JButton();
        mute = new JButton();
        addToPlaylist = new JButton();
        addToFavorites = new JButton();

        initializeButton(shuffle , "src\\Icons\\Shuffle.png");
        initializeButton(previous , "src\\Icons\\Previous.png");
        initializeButton(playPause , "src\\Icons\\PlayPause.png");
        initializeButton(next , "src\\Icons\\Next.png");
        initializeButton(repeat , "src\\Icons\\Repeat.png");
        initializeButton(mute , "src\\Icons\\Mute.png");
        initializeButton(addToPlaylist , "src\\Icons\\AddToPlaylist.png");
        initializeButton(addToFavorites , "src\\Icons\\AddToFavorites.png");

        mute.setText("Mute");
        addToPlaylist.setText("Add To Playlist");
        addToFavorites.setText("Add To Favorites");
    }

    private Song findSong(int flag){
        switch (flag){
            case REPEAT_SONG:
                return nowPlayingSong ;
            case SHUFFLE_SONG :
                return songQueue.get(new Random().nextInt(songQueue.size()));
        }
        int counter = 0 ;
        for(Song song : songQueue){
            if(song.equals(nowPlayingSong)){
                break;
            }
            counter++ ;
        }
        switch (flag){
            case PREVIOUS_SONG :
                if(counter < songQueue.size() - 1)
                    return songQueue.get(counter + 1);
                break;
            case NEXT_SONG :
                if(counter > 0)
                    return songQueue.get(counter - 1);
        }
        return null ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(playPause)){
            if(timer == null) {
                timer = new SongTimer(songSlider , songCurrentTimePassed);
                timer.setTask();
                timer.setMaxTime((int)nowPlayingSong.getLengthInSeconds());
                timer.setSongStatus(isPlaying);
            }
            try {
                if(!isPlaying) {
                    player.play();
                    isPlaying = true ;
                    timer.setSongStatus(true);
                    timer.start();
                }
                else {
                    player.pause();
                    isPlaying = false ;
                    timer.setSongStatus(false);
                }
            }catch (Exception ignored){}
        }
        if (e.getSource().equals(next)) {
                Song nextSong = findSong(NEXT_SONG);
                if(nextSong != null)
                    updatePanel(nextSong);
        }
        if(e.getSource().equals(previous)) {
            Song previousSong = findSong(PREVIOUS_SONG);
            if(previousSong != null)
                updatePanel(previousSong);
        }
        if(e.getSource().equals(repeat)){
            if(isRepeat){
                isRepeat = false ;
                repeat.setBackground(new Color(0x3E769C));
            }
            else {
                isShuffle = false ;
                shuffle.setBackground(new Color(0x3E769C));
                isRepeat = true ;
                repeat.setBackground(new Color(0x344C68));
            }
        }
        if(e.getSource().equals(shuffle)){
            if(!isShuffle){
                isRepeat = false ;
                repeat.setBackground(new Color(0x3E769C));
                isShuffle = true ;
                shuffle.setBackground(new Color(0x344C68));
            }
            else {
                isShuffle = false ;
                shuffle.setBackground(new Color(0x3E769C));
            }
        }
        if(e.getSource().equals(mute)){
            try {
                Thread.sleep(10);
            }catch (InterruptedException ignored){}
            if(isMute) {
                player.setVolume(volumeValue);
                mute.setBackground(new Color(0x3E769C));
                isMute = false ;
            }
            else {
                player.setVolume(-80);
                mute.setBackground(new Color(0x344C68));
                isMute = true ;
            }
        }
        if(e.getSource().equals(addToPlaylist)){
            MainPanel.updateSongsPanel();
            showSongsPanel.createNorthPanel("AddToPlaylist");
            showSongsPanel.updatePanelByPlaylist(new AddToPlaylistListener());
        }
        if(e.getSource().equals(addToFavorites)){
            if(Library.favorites.addSong(nowPlayingSong))
                addToFavorites.setBackground(new Color(0x344C68));
            else{
                Library.favorites.removeSong(nowPlayingSong);
                addToFavorites.setBackground(new Color(0x3E769C));
            }
            if(showSongsPanel.getOptionPanelType().equals("Favorites"))
                showSongsPanel.updatePanelBySong(Library.favorites.getSongs() , showSongsPanel);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        volumeValue = ((JSlider)e.getSource()).getValue() ;
        if(!isMute)
            player.setVolume(volumeValue);
    }

    private class AddToPlaylistListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton) e.getSource();
            ShowSongsPanel.getPlaylistByButton.get(buttonPressed).addSong(nowPlayingSong);
            showSongsPanel.createNorthPanel("Library");
            showSongsPanel.updatePanelBySong(Library.allSongs , showSongsPanel);
        }
    }

    private class SongTimer {

        private Timer timer ;
        private TimerTask task ;
        private boolean isPlaying ;
        private int songLength ;
        private JSlider slider ;
        private JLabel timePassed ;

        public SongTimer(JSlider slider , JLabel timePassed) {
            timer = new Timer();
            this.slider = slider ;
            this.timePassed = timePassed ;
            slider.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent event) {
                    super.mouseReleased(event);
                    try {
                        player.close();
                        player = new PausablePlayer(nowPlayingSong , slider.getValue());
                        player.play();
                        Thread.sleep(10);
                        if(isMute)
                            player.setVolume(-80);
                        else
                            player.setVolume(volumeValue);

                    }catch (JavaLayerException | FileNotFoundException | InterruptedException e){e.printStackTrace();}
                }
            });
        }

        public void setSongStatus(boolean isPlaying){
            this.isPlaying = isPlaying ;
        }

        public void setMaxTime(int songLength) {
            this.songLength = songLength;
        }

        public void setTask(){
            task = new TimerTask() {

                @Override
                public void run() {
                    if (isPlaying){
                        slider.setValue(slider.getValue() + 1);
                        timePassed.setText(getTimePassed());
                        if(slider.getValue() == songLength)
                            updatePanelByDefault();
                    }
                }
            };
        }

        public void start(){
            timer.scheduleAtFixedRate(task,0,1000);
        }

        private String getTimePassed(){
            int timePassed = slider.getValue();
            int minute = timePassed / 60 ;
            int second = timePassed  - ( minute * 60) ;

            return minute + ":" + ( (second<10) ? ("0" + second) : (second) ) ;
        }

    }

}