package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;
import NetWork.Client;

import java.util.ArrayList;
import javax.swing.*;

public class Test extends JSlider {

    public Test() {
    }

    public static void main(String[] args) throws Exception {
        Song song = new Song("Sirvan Khosravi Tajob Nakon.mp3");          //temporary
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
       // Thread.sleep(3000);
        //mainPanel.playerPanel.updatePanel(new Song("Sirvan Khosravi Tajob Nakon.mp3"));
        Library library = new Library();
        new Client();

    }
}