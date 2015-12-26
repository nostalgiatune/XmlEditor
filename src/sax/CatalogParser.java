
package sax;

import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class CatalogParser
    extends DefaultHandler {
    
    enum Elements { CATALOG, CD, TITLE, ARTIST, COUNTRY, COMPANY, YEAR, PRICE, RATING, NONE };
    
    private Elements processing;
    private Album constructedAlbum;
    private Catalog catalog;

    @Override
    public void startDocument() throws SAXException {
        catalog = new Catalog();
    }
  
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

          switch (qName) {
              case "catalog":
                  processing = Elements.CATALOG;
                  break;
              case "cd":
                  processing = Elements.CD;
                  constructedAlbum = new Album();
                  catalog.addAlbum(constructedAlbum);
                  break;
              case "title":
                  processing = Elements.TITLE;
                  break;
              case "artist":
                  processing = Elements.ARTIST;
                  break;
              case "country":
                  processing = Elements.COUNTRY;
                  break;
              case "company":
                  processing = Elements.COMPANY;
                  break;
              case "year":
                  processing = Elements.YEAR;
                  break;
              case "price":
                  processing = Elements.PRICE;
                  break;
              case "rating:rating":
                  processing = Elements.RATING;
                  break;
              default:
                  break;
          }
    }

      @Override
    public void endElement(String url, String localName, String qName) throws
        SAXException {
          processing = Elements.NONE;
    }

      @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

      String s = new String(ch, start, length);
      s = s.trim();
      if (s.equals(""))
          return;

      switch (processing) {
          case CD:
              break;
          case TITLE:
              constructedAlbum.setTilte(s);
              break;
          case ARTIST:
              constructedAlbum.setArtist(s);
              break;
          case COUNTRY:
              constructedAlbum.setCountry(s);
              break;
          case COMPANY:
              constructedAlbum.setCompany(s);
              break;
          case YEAR:
              try {
                  int year = Integer.parseInt(s);
                  constructedAlbum.setYear(year);
              }catch (Exception e) {
                  System.out.println("Int Muunnos epäonnistui");
              }
              break;
          case PRICE:
              try {
                  double price = Double.parseDouble(s);
                  constructedAlbum.setPrice(price);
              }catch (Exception e) {
                  System.out.println("Muunnos epäonnistui");
              }
              break;
          case RATING:
              constructedAlbum.setRating(s);
              break;
          case NONE:
              break;
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }
}

