package Logic;

import GUI.MainPanel;
import GUI.PlayerPanel;
import Network.Client;

import javax.swing.*;

public class Main {
    /**
     * includes the main method
     * gets the username at first
     * needs a first song to create the whole panel
     */
    public static String userName ;
    private static MainPanel mainPanel ;

    private static void getUserName(){
        userName = SaveData.getUserName();
        if(userName == null || userName.equals("null")) {
            userName = JOptionPane.showInputDialog(null, "Enter New UserName", "Login", JOptionPane.INFORMATION_MESSAGE);
            SaveData.saveUserName(userName);
        }
    }

    public static void main(String[] args) throws Exception {
        Song song = SaveData.getLastSong();
        if(song == null)
            song = new Song("src\\SavedData\\Kouli.mp3");
        PlayerPanel.setSong(song);
        SaveData.retrieveData();
        getUserName();
        mainPanel = new MainPanel();
        mainPanel.setVisible(true);
        new Client();
    }
}