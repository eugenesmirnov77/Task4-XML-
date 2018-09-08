package by.smirnov.taskXML.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class SchemaValidator {

    private static final Logger logger = LogManager.getLogger(SchemaValidator.class);

    public static void validateXMLByXSD(String xmlPath, String xsdPath) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new File(xsdPath))
                    .newValidator()
                    .validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException e) {
            e.printStackTrace();
            logger.log(Level.ERROR, "Not valid");
            return;
        } catch (IOException e) {
            e.getMessage();
            logger.log(Level.ERROR, "File error");
            return;
        }
        logger.log(Level.ERROR, "XML is valid");
    }
}
