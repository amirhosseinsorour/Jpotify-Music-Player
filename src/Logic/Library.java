package Logic;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.SortedMap;

public class Library {

    public static ArrayList<Song> allSongs ;
    public static ArrayList<Album> albums ;
    public static ArrayList<Artist> artists ;
    public static ArrayList<Playlist> playlists ;

    public Library() {
        allSongs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        artists = new ArrayList<Artist>();
        playlists = new ArrayList<Playlist>();
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

    public void addNewPlaylist(String name){
        Playlist newPlaylist = new Playlist(name);
        playlists.add(newPlaylist);
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

    public static Artist findArtist(String name){
        for (Artist artist : artists) {
            if (artist.getName().equals(name))
                return artist;
        }
        return null ;
    }

    public static Album findAlbum(String name){
        for (Album album : albums) {
            if (album.getName().equals(name))
                return album;
        }
        return null ;
    }

    public static Playlist findPlaylist(String name){
        for(Playlist playlist : playlists){
            if(playlist.getName().equals(name))
                return playlist ;
        }
        return null ;
    }
}
