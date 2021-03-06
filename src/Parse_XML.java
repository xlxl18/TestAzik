import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class Parse_XML extends DefaultHandler {

    public static void main(String args[]) {
         List<String> prices = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        // Имя файла
        final String fileName = "Products.xml";

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

           
            DefaultHandler handler = new DefaultHandler() {
                
                boolean price = false;
                boolean title = false;

               
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    
                    if (qName.equalsIgnoreCase("price")) {
                        price = true;
                    }
                    else if(qName.equalsIgnoreCase("title")) {
                        title = true;
                    }
                }

               
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    
                    if (price) {
                       prices.add(new String(ch, start, length));
                        price = false;
                    }
                    else if(title) {
                        titles.add(new String(ch ,start ,length));
                    }
                }
            };

            
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        product1.setPrice(Double.parseDouble(prices.get(0)));
        product1.setTitle(titles.get(0));
        product2.setPrice(Double.parseDouble(prices.get(1)));
        product2.setTitle(titles.get(1));
        product3.setPrice(Double.parseDouble(prices.get(2)));
        product3.setTitle(titles.get(2));
        product4.setPrice(Double.parseDouble(prices.get(3)));
        product4.setTitle(titles.get(3));
    }
}
