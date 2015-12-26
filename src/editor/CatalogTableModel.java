package editor;

import javax.swing.table.AbstractTableModel;
import sax.*;

public class CatalogTableModel extends AbstractTableModel {
    
    private final Catalog catalog;
    private final String[] columns = { "Title", "Artist", "Country", "Company",
        "Price", "Year", "Rating" };

    public CatalogTableModel(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public int getRowCount() {
        return catalog.getAlbumCount();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Album album = catalog.getAlbumByIndex(rowIndex);
        
        switch (columnIndex) {
            case 0: return album.getTitle();
            case 1: return album.getArtist();
            case 2: return album.getCountry();
            case 3: return album.getCompany();
            case 4: return album.getPrice();
            case 5: return album.getYear();
            case 6: return album.getRating();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Album album = catalog.getAlbumByIndex(rowIndex);
        switch (columnIndex) {
            case 0: album.setTilte((String) aValue); break;
            case 1: album.setArtist((String) aValue); break;
            case 2: album.setCountry((String) aValue); break;
            case 3: album.setCompany((String) aValue); break;
            case 4: album.setPrice((Double) aValue); break;
            case 5: album.setYear((Integer) aValue); break;
            case 6: album.setRating((String) aValue); break;
            default: break;
        }
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Double.class;
            case 5: return Integer.class;
            case 6: return String.class;
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void addAlbum(Album album) {
        catalog.addAlbum(album);
        fireTableRowsInserted(catalog.getAlbumCount()-1, catalog.getAlbumCount()-1);
    }
    
    public void deleteAlbum(int row) {
        catalog.deleteAlbum(row);
        fireTableRowsDeleted(row, row);
    }
}