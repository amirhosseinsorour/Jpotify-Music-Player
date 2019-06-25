package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class Test extends JSlider {

    public Test() {
    }

    public static void main(String[] args) throws Exception {
        Song song = new Song("Sirvan Khosravi Na Naro.mp3");          //temporary
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        Thread.sleep(3000);
        mainPanel.playerPanel.updatePanel(new Song("kheili rooza gozasht.mp3"));
        Library library = new Library();

    }
}