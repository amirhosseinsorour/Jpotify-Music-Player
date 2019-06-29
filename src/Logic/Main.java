package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import Network.Client;

public class Main {

    public static void main(String[] args) throws Exception {
        Song song = SaveData.getLastSong();
        if(song == null)
            song = new Song("src\\SavedData\\Kouli.mp3");
        PlayerPanel.setSong(song);
        SaveData.retriveData();
//        ShowSongsPanel.songsToShow = new ArrayList<>();
        MainPanel mainPanel = new MainPanel();
        new Client();
    }
}