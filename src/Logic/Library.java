package Logic;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedMap;

public class Library {

    public static ArrayList<Song> allSongs ;
    public static ArrayList<Album> albums ;
    public static ArrayList<Artist> artists ;

    public Library() {
        allSongs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        artists = new ArrayList<Artist>();
    }

    public static void addSong(Song song){
        if(isNewSong(song))
            allSongs.add(song);
        else return ;

        Album album = isNewAlbum(song);
        Artist artist = isNewArtist(song);

        if(album != null){
            album.addSong(song);
        }
        else {
            album = new Album(song.getAlbum());
            album.addSong(song);
            albums.add(album);
        }

        if(artist != null){
            artist.addSong(song);
        }
        else {
            artist = new Artist(song.getArtist());
            artist.addSong(song);
            artists.add(artist);
        }
    }

    public void removeSong(Song song) {
        if (allSongs.contains(song)) {
            allSongs.remove(song);
        }
    }

    private static Album isNewAlbum(Song song){
        String albumName = song.getAlbum() ;
        try {
            for (Album album : albums) {
                if (album.getName().equals(albumName))
                    return album;
            }
        }catch (NullPointerException ignored){}
        return null ;
    }

    private static Artist isNewArtist(Song song){
        String artistName = song.getArtist() ;
        try {
            for (Artist artist : artists) {
                if (artist.getName().equals(artistName))
                    return artist;
            }
        }catch (NullPointerException ignored){}
        return null ;
    }

    private static boolean isNewSong(Song newSong){
        for (Song song : allSongs){
            if(song.equals(newSong))
                return false ;
        }
        return true ;
    }

/*    public static Song findSong(String title){
        for (Song song : allSongs) {
            if (song.getTitle().equals(title))
                return song;
        }
        return null ;
    }

    public static Album findAlbum(String title){
        for (Album album : albums) {
            if (album.getName().equals(title))
                return album;
        }
        return null ;
    }*/
}
