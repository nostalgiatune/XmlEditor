package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import editor.*;

class Main {
    
    public static void main(String[] args) {
        
        Catalog catalog = null; // Constructed by CatalogParser
        
        // MVC model for editor
        CatalogTableModel model; // Table model for editor's JTable, backed up by catalog
        CatalogController controller;
        CatalogEditor editor; // User interface
        CatalogPersister persister; // Writes data to XML when table model is changed
      
        String uri = "src\\sax\\catalog.xml";
        try {
            catalog = parseCatalog(uri);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        model = new CatalogTableModel(catalog);
        editor = new CatalogEditor();
        controller = new CatalogController(model, editor);
        editor.setController(controller);
        
        persister = new CatalogPersister(catalog, "updatedCatalog.xml");
        controller.setPersister(persister);
    }
    
    public static Catalog parseCatalog(String uri) throws Exception {
        
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        parserFactory.setNamespaceAware(false);
        parserFactory.setFeature(
           "http://apache.org/xml/features/validation/schema", true);
        CatalogParser catalogParser = new CatalogParser();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse(uri, catalogParser);
            
        return catalogParser.getCatalog();
    }
}