package GUI;

import Logic.Song;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {

    public LeftToolbar leftToolbar ;
    public PlayerPanel playerPanel ;
    public static ShowSongsPanel showSongsPanel ;
    private static JScrollPane scrollPane ;

    public MainPanel() throws Exception{
        super("JPotify Music Player");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src\\Icons\\JPotifyApp.png").getImage());
        setSize(1600,900);
        setLocation(50,50);
        setLayout(new BorderLayout());

        showSongsPanel = new ShowSongsPanel();
        playerPanel = new PlayerPanel();

        playerPanel.setShowSongsPanel(showSongsPanel);
        add(playerPanel , BorderLayout.SOUTH);

        showSongsPanel.setPlayerPanel(playerPanel);
        scrollPane = new JScrollPane(showSongsPanel);
        add(scrollPane , BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftToolbar = new LeftToolbar();
        leftToolbar.setShowSongsPanel(showSongsPanel);
        leftPanel.add(leftToolbar , BorderLayout.NORTH);

        JPanel justForColor = new JPanel();
        justForColor.setBackground(new Color(0xD6000821,true));
        leftPanel.add(justForColor , BorderLayout.CENTER);

        add(leftPanel , BorderLayout.WEST);

    }

    public static void updateSongsPanel(){
        showSongsPanel = new ShowSongsPanel();
        scrollPane = new JScrollPane(showSongsPanel);
    }

}
