package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import GUI.ShowSongsPanel;
import Network.Client;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        Song song = SaveData.getLastSong();
        if(song == null)
            song = new Song("src\\SavedData\\Kouli.mp3");
        PlayerPanel.setSong(song);
        ShowSongsPanel.songsToShow = new ArrayList<>();
        Library library = new Library();
        SaveData.retriveData();
        MainPanel mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        new Client();
    }
}