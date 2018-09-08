package by.smirnov.taskXML.runner;

import by.smirnov.taskXML.entity.Tariffs;
import by.smirnov.taskXML.util.SchemaValidator;
import by.smirnov.taskXML.util.TariffsDOMBuilder;
import by.smirnov.taskXML.util.TariffsHandler;
import by.smirnov.taskXML.util.TariffsStax;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Runner {

    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        logger.log(Level.INFO, "Validator\n---------");

        String filePath = "data/Rates.xml";
        String schemaPath = "data/Rates.xsd";
        SchemaValidator.validateXMLByXSD(filePath, schemaPath);

        logger.log(Level.INFO, "SAX Parser\n---------");

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            TariffsHandler handler = new TariffsHandler();
            saxParser.parse(new File(filePath), handler);

            List<Tariffs> tariffs = handler.getTariffs();
            for (Tariffs current : tariffs) {
                logger.log(Level.INFO, current);
            }
        } catch (ParserConfigurationException | SAXException | IOException exc) {
            exc.printStackTrace();
        }

        logger.log(Level.INFO, "DOM Parser\n---------");

        TariffsDOMBuilder DOM = new TariffsDOMBuilder();
        DOM.buildSetTariffs(filePath);

        logger.log(Level.INFO, "Stax Parser\n---------");

        TariffsStax tariffsStax = new TariffsStax();
        tariffsStax.buildListTariffs(filePath);



    }
}
 