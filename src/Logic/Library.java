package Logic;

import java.util.ArrayList;
import java.util.HashSet;

public class Library {

    public static ArrayList<Song> allSongs ;
    public static ArrayList<Album> albums ;

    public Library() {
        allSongs = new ArrayList<>();
        albums = new ArrayList<>();
    }

    public static void addSong(Song song){
        allSongs.add(song);
        Album album = isAlbum(song);
        if(album != null){
            album.addSong(song);
        }
        else {
            album = new Album(song.getAlbum());
            album.addSong(song);
        }
    }

    public void removeSong(Song song) {
        if (allSongs.contains(song)) {
            allSongs.remove(song);
        }
    }

    private static Album isAlbum(Song song){
        String albumName = song.getAlbum() ;
        try {
            for (Album album : albums) {
                if (album.getName().equals(albumName))
                    return album;
            }
        }catch (NullPointerException ignored){}
        return null ;
    }
}
