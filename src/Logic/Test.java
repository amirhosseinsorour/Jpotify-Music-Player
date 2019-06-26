package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;

import java.util.ArrayList;
import javax.swing.*;

public class Test extends JSlider {

    public Test() {
    }

    public static void main(String[] args) throws Exception {
        Song song = new Song("Homayoun-Shajarian-The-Lords-of-the-Secrets-Tasnif-on-Rumi-S.mp3");          //temporary
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        Thread.sleep(3000);
        mainPanel.playerPanel.updatePanel(new Song("Ebi - Jane Javani.mp3"));
        Library library = new Library();

    }
}