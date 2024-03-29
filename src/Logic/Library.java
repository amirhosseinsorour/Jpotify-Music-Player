package Logic;

import java.util.ArrayList;

public class Library {
    /**
     * includes all songs , albums , artists
     */

    public static ArrayList<Song> allSongs = new ArrayList<>();
    public static ArrayList<Album> albums = new ArrayList<>() ;
    public static ArrayList<Artist> artists = new ArrayList<>();
    public static ArrayList<Playlist> playlists = new ArrayList<>();
    public static Favorites favorites = new Favorites();
    public static SharedPlaylist sharedPlaylist = new SharedPlaylist();

    /**
     * adds song to library
     * adds song to its album or artist arraylist
     * @param song is added to library if new
     */
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

    /**
     * checks if songs album is new or not
     * @return the song's album if exists
     */

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

    /**
     * checks if songs artist is new or not
     * @return the song's artist if exists
     */

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

    /**
     * checks if songs new or not
     * @return true if found
     */

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
