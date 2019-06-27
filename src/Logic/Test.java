package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;
import Network.Client;

import java.util.ArrayList;
import javax.swing.*;

public class Test extends JSlider {

    public Test() {
    }

    public static void main(String[] args) throws Exception {
        Song song = new Song("Ebi - Jane Javani.mp3");          //temporary
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        Library library = new Library();
        new Client();

    }
}