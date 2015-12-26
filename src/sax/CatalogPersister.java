package sax;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

// Writes catalog to XML when table model is changed
public class CatalogPersister implements TableModelListener {
    
    private final Catalog catalog;
    private final String filename;
    
    private FileWriter file;
    private PrintWriter fileWriter;

    public CatalogPersister(Catalog catalog, String filename) {
        this.catalog = catalog;
        this.filename = filename;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println("Table changed, persisting file");
        persist();
    }
    
    public void persist() {
        try {
            file = new FileWriter(filename);
            fileWriter = new PrintWriter(file);
            
            fileWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            fileWriter.println("<?xml-stylesheet type=\"text/xsl\" href=\"liststyle.xsl\"?>");
            fileWriter.println("<catalog xmlns=\"users.metropolia.fi/~tuomavt/catalog\"\n" +
"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
"xmlns:rating=\"users.metropolia.fi/~tuomavt/rating\"\n" +
"xsi:schemaLocation=\"users.metropolia.fi/~tuomavt/catalog catalog.xsd\">");
            fileWriter.println();
            
            Iterator<Album> catalogIterator = catalog.getIterator();
            Album album;
            while (catalogIterator.hasNext()) {
                album = catalogIterator.next();
                fileWriter.println("    <cd>");
                fileWriter.println("        <title>" + album.getTitle() + "</title>");
                fileWriter.println("        <artist>" + album.getArtist() + "</artist>");
                fileWriter.println("        <country>" + album.getCountry() + "</title>");
                fileWriter.println("        <company>" + album.getCompany() + "</company>");
                fileWriter.println("        <price>" + album.getPrice() + "</price>");
                fileWriter.println("        <year>" + album.getYear() + "</year>");
                fileWriter.println("        <rating:rating>" + album.getRating() + "</rating:rating>");
                fileWriter.println("    </cd>");
                fileWriter.println();
            }
            fileWriter.println("</catalog>");
            fileWriter.flush();
            
        } 
        catch (Exception ex) {
            System.out.println("Couldn't open file");
        }
        finally {
            try {
                fileWriter.close();
                file.close();
            } catch (IOException ex) {
            }
        }
    }
}