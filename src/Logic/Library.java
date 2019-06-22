package Logic;

import java.util.HashSet;

public class Library {

    public static HashSet<Song> allSongs ;
    public static HashSet<Album> albums ;

    public Library() {
        allSongs = new HashSet<Song>();
        albums = new HashSet<Album>();
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
