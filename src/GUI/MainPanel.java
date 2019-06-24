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
        setSize(1600,900);
        setLocation(50,50);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0x7EB4D3));

        playerPanel = new PlayerPanel();
        add(playerPanel , BorderLayout.SOUTH);

        showSongsPanel = new ShowSongsPanel();
        showSongsPanel.setPlayerPanel(playerPanel);
        scrollPane = new JScrollPane(showSongsPanel);
        add(scrollPane , BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftToolbar = new LeftToolbar();
        leftToolbar.setShowSongsPanel(showSongsPanel);
        leftPanel.add(leftToolbar , BorderLayout.NORTH);
//        JScrollPane toolbarScrollPane = new JScrollPane(leftPanel);
//        toolbarScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//        toolbarScrollPane.setBackground(new Color(0xD3B599C7));

        JPanel justForColor = new JPanel();
        justForColor.setBackground(new Color(0xD3B599C7,true));
        leftPanel.add(justForColor , BorderLayout.CENTER);

        add(leftPanel , BorderLayout.WEST);

    }

    public static void updateSongsPanel(){
        showSongsPanel = new ShowSongsPanel();
        scrollPane = new JScrollPane(showSongsPanel);
    }

}
