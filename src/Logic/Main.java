package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import Network.Client;

import javax.swing.*;

public class Main {
    public static String userName ;
    private static MainPanel mainPanel ;

    private static void getUserName(){
        userName = SaveData.getUserName();
        if(userName == null || userName.equals("null")) {
            userName = JOptionPane.showInputDialog(null, "Enter New UserName", "Login", JOptionPane.INFORMATION_MESSAGE);
            SaveData.saveUserName(userName);
        }
        mainPanel.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Song song = SaveData.getLastSong();
        if(song == null)
            song = new Song("src\\SavedData\\Kouli.mp3");
        PlayerPanel.setSong(song);
        SaveData.retrieveData();
        mainPanel = new MainPanel();
        getUserName();
        new Client();
    }
}