package editor;

import sax.Album;
import sax.CatalogPersister;

public class CatalogController {
    
    private CatalogTableModel model;
    private CatalogEditor editor;
    
    // Optional
    private boolean persist = false;
    private CatalogPersister persister;

    public CatalogController(CatalogTableModel model, CatalogEditor editor) {
        this.model = model;
        this.editor = editor;
    }
    
    public CatalogTableModel getModel() {
        return model;
    }
    
    public CatalogEditor getEditor() {
        return editor;
    }

    public void setPersister(CatalogPersister persister) {
        this.persister = persister;
        persist = true;
        model.addTableModelListener(persister);
        persister.tableChanged(null);
    }
    
    public void addAlbum() {
        Album album = new Album();
        model.addAlbum(album);
    }
    
    public void deleteAlbum(int row) {
        model.deleteAlbum(row);
    }
}