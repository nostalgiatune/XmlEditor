package sax;

import java.util.ArrayList;
import static java.util.Comparator.*;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.*;

public class Catalog {
    
    private final ArrayList<Album> albums;
    
    public Catalog() {
        albums = new ArrayList();
    }
    
    public void addAlbum(Album album) {
        albums.add(album);
    }
    
    public void deleteAlbum(int row) {
        albums.remove(row);
    }
    
    public int getAlbumCount() {
        return albums.size();
    }
    
    public Album getAlbumByIndex(int index) {
        return albums.get(index);
    }
    
    public Album getAlbumByTitle(String title) {
        for (Album album : albums) {
            if (album.getTitle().equals(title))
                return album;
        }
        return null;
    }
    
    public List<Album> getAlbumsByArtist(String artist) {
        
        List<Album> results = albums.stream()
                .filter(album->album.getArtist().equals(artist))
                .sorted(comparing(Album::getArtist))
                .collect(toList());
        
        return results;
    }
    
    public void print() {
        
        for (Album album : albums) {
            album.print();
            System.out.println();
        }
    }
    
    public Iterator<Album> getIterator() {
        return albums.iterator();
    }
}
