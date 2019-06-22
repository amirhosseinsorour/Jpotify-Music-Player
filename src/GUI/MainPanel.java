package GUI;

import Logic.Song;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {

    public LeftToolbar leftToolbar ;
    public PlayerPanel playerPanel ;
    public ShowSongsPanel showSongsPanel ;

    public MainPanel() throws Exception{
        super("JPotify Music Player");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1600,900);
        setLocation(50,50);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0x7EB4D3));

        showSongsPanel = new ShowSongsPanel();
        JScrollPane scrollPane = new JScrollPane(showSongsPanel);
        add(scrollPane , BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftToolbar = new LeftToolbar();
        leftToolbar.setShowSongsPanel(showSongsPanel);
        leftPanel.add(leftToolbar , BorderLayout.NORTH);
        add(leftPanel , BorderLayout.WEST);

        playerPanel = new PlayerPanel();
        add(playerPanel , BorderLayout.SOUTH);
    }

}
