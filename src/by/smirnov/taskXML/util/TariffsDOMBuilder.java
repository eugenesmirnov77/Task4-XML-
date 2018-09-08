package by.smirnov.taskXML.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TariffsDOMBuilder {

    private static final Logger logger = LogManager.getLogger(SchemaValidator.class);

    private DocumentBuilder documentBuilder;

    public TariffsDOMBuilder() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException exc) {
            logger.log(Level.ERROR, "Error " + exc);
        }

    }

    public void buildSetTariffs(String fileName) {
        Document doc;

        try {

            doc = documentBuilder.parse(fileName);
            doc.getDocumentElement().normalize();

            logger.log(Level.INFO, "Root element: " + doc.getDocumentElement().getNodeName());

            Element root = doc.getDocumentElement();

            NodeList tariffsList = root.getElementsByTagName("tariff");



            for (int i = 0; i < tariffsList.getLength(); i++) {

                Node node = tariffsList.item(i);
                logger.log(Level.INFO, "Current element :" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    logger.log(Level.INFO, "Name : " + element.getElementsByTagName("name").item(0).getTextContent());
                    logger.log(Level.INFO, "Operator : " + element.getElementsByTagName("operator").item(0).getTextContent());
                    logger.log(Level.INFO, "Payroll : " + element.getElementsByTagName("payroll").item(0).getTextContent());
                    logger.log(Level.INFO, "Call Prices: Internal : " + element.getElementsByTagName("internal").item(0).getTextContent());
                    logger.log(Level.INFO, "External : " + element.getElementsByTagName("external").item(0).getTextContent());
                    logger.log(Level.INFO, "Fixed phones : " + element.getElementsByTagName("fixed-phones").item(0).getTextContent());
                    logger.log(Level.INFO, "SMS price : " + element.getElementsByTagName("SMS-price").item(0).getTextContent());
                    logger.log(Level.INFO, "Parametres: Favourite numbers : " + element.getElementsByTagName("favourite-number").item(0).getTextContent());
                    logger.log(Level.INFO, "Tariffings : " + element.getElementsByTagName("tariffing").item(0).getTextContent());
                    logger.log(Level.INFO, "Connection fee : " + element.getElementsByTagName("connection-fee").item(0).getTextContent());
                    logger.log(Level.INFO, "Date of connect : " + element.getElementsByTagName("Date-connect").item(0).getTextContent());

                }

            }

        } catch (IOException e) {
            logger.log(Level.ERROR,"File error or I/O error: " + e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parsing failure: " + e);
        }
    }
}
