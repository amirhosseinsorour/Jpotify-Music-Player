package Logic;

import GUI.PlayerPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveData {

    /**
     * save all songs in the library with address
     */
    public static void saveLibrary() {
        savePlaylist(Library.allSongs , "LibrarySongs");
    }

    /**
     *saves just the names of the playlists
     */

    public static void savePlaylists() {
        try {
            PrintWriter playlistNameWriter = new PrintWriter(new FileWriter("src\\SavedData\\PlaylistsName.txt" , false));
            for(Playlist playlist : Library.playlists){
                String playlistName = playlist.getName();
                playlistNameWriter.println(playlistName);
            }
            playlistNameWriter.close();
        } catch (IOException ignored){}
    }

    /**
     * deletes playlist file when playlist is removed
     * @param playlist is removed of saved data
     */
    public static void deletePlaylist(Playlist playlist){
        File playlistToRemove = new File("src\\SavedData\\Playlists\\" + playlist.getName() + ".txt");
        playlistToRemove.delete();
    }

    /**
     *saves address of all songs in a certain playlist
     * @param songs all songs in a certain playlist
     */

    public static void savePlaylist(ArrayList<Song> songs , String path){
        try {
            PrintWriter playlistWriter = new PrintWriter(new FileWriter("src\\SavedData\\" + path + ".txt" ,false));
            for(Song song : songs){
                playlistWriter.println(song.getAddress());
            }
            playlistWriter.close();
        }catch (IOException e){e.printStackTrace();}
    }

    /**
     * saves the last played song
     * @param song the last song played
     */
    public static void saveLastSong(Song song){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("src\\SavedData\\LastPlayedSong.txt" , false));
            printWriter.println(song.getAddress());
            printWriter.close();
        }catch (IOException ignored){}
    }

    public static Song getLastSong(){
        try {
            return new Song(new Scanner(new FileReader("src\\SavedData\\LastPlayedSong.txt")).nextLine());
        }catch (Exception ignored){
            return null;
        }
    }

    /**
     * @return username if exists
     */

    public static String getUserName(){
        String userName ;
        try {
            userName = new Scanner(new FileReader("src\\SavedData\\Username.txt")).nextLine();
        }catch (Exception ignored){
            return null ;
        }
        return (userName.isEmpty()) ? null : userName ;
    }

    /**
     * @param userName username is saved
     */
    public static void saveUserName(String userName){
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter("src\\SavedData\\Username.txt" , false));
            printWriter.println(userName);
            printWriter.close();
        }catch (IOException ignored){}
    }

    /**
     * retrieves all data saved in the last run
     */

    public static void retrieveData(){
        try {
            Scanner scanner = new Scanner(new FileReader("src\\SavedData\\LibrarySongs.txt"));
            while (scanner.hasNext()){
                String songAddress = scanner.nextLine();
                try {
                    Song newSong = new Song(songAddress);
                    Library.addSong(newSong);
                }catch (Exception ignored){}
            }

            scanner = new Scanner(new FileReader("src\\SavedData\\PlaylistsName.txt"));
            while (scanner.hasNext()){
                String playlistName = scanner.nextLine();
                Scanner playlistScanner = new Scanner(new FileReader("src\\SavedData\\Playlists\\" + playlistName + ".txt"));
                Playlist newPlaylist = new Playlist(playlistName);
                while (playlistScanner.hasNext()){
                    try {
                        newPlaylist.addSong(new Song(playlistScanner.nextLine()));
                    }catch (Exception ignored){}
                }
                Library.playlists.add(newPlaylist);
            }

            scanner = new Scanner(new FileReader("src\\SavedData\\Favorites.txt"));
            while (scanner.hasNext()){
                try {
                    Library.favorites.addSong(new Song(scanner.nextLine()));
                }catch (Exception ignored){}
            }

            scanner = new Scanner(new FileReader("src\\SavedData\\SharedPlaylist.txt"));
            while (scanner.hasNext()){
                try {
                    Library.sharedPlaylist.addSong(new Song(scanner.nextLine()));
                }catch (Exception ignored){}
            }

            PlayerPanel.songQueue = Library.allSongs ;
        }catch (FileNotFoundException ignored){}
    }
}
