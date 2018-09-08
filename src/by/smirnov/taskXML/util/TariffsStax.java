package by.smirnov.taskXML.util;

import by.smirnov.taskXML.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TariffsStax {

    private static final Logger logger = LogManager.getLogger(TariffsStax.class);

    private List<Tariffs> tariffs;
    private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

    public void buildListTariffs(String fileName) {

        tariffs = parseXML(fileName);
        for (Tariffs tariff : tariffs) {
            logger.log(Level.INFO, tariff.toString());
        }
    }

    private List<Tariffs> parseXML(String fileName) {

        tariffs = new ArrayList<>();
        Tariffs tariff = null;
        CallPrice callPrice = null;
        Params params = null;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "tariff":
                            tariff = new Tariffs();
                            break;
                        case "name":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (tariff != null) {
                                tariff.setName(xmlEvent.asCharacters().getData());
                            }
                            break;
                        case "operator":
                            xmlEvent = xmlEventReader.nextEvent();
                            switch (xmlEvent.asCharacters().getData()) {
                                case "MTS":
                                    if (tariff != null) {
                                        tariff.setOperator(Operator.MTS);
                                    }
                                    break;
                                case "Velcom":
                                    if (tariff != null) {
                                        tariff.setOperator(Operator.VELCOM);
                                    }
                                    break;
                                case "Life":
                                    if (tariff != null) {
                                        tariff.setOperator(Operator.LIFE);
                                    }
                                    break;
                            }
                            break;
                        case "payroll":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (tariff != null) {
                                tariff.setPayroll(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            break;
                        case "call-prices":
                            callPrice = new CallPrice();
                            break;
                        case "internal":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (callPrice != null) {
                                callPrice.setInternal(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            if (tariff != null) {
                                tariff.setCallPrice(callPrice);
                            }
                            break;
                        case "external":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (callPrice != null) {
                                callPrice.setExternal(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            if (tariff != null) {
                                tariff.setCallPrice(callPrice);
                            }
                            break;
                        case "fixed-phones":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (callPrice != null) {
                                callPrice.setFixedPhones(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            if (tariff != null) {
                                tariff.setCallPrice(callPrice);
                            }
                            break;
                        case "SMS-price":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (tariff != null) {
                                tariff.setSmsPrice(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            break;
                        case "parameters":
                            params = new Params();
                            break;
                        case "favourite-number":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (params != null) {
                                params.setFavouriteNum(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            }
                            if (tariff != null) {
                                tariff.setParams(params);
                            }
                            break;
                        case "tariffing":
                            xmlEvent = xmlEventReader.nextEvent();
                            switch (xmlEvent.asCharacters().getData()) {
                                case "1-min":
                                    if (params != null) {
                                        params.setTariffings(Tariffings.ONE_MINUTE);
                                    }
                                    if (tariff != null) {
                                        tariff.setParams(params);
                                    }
                                    break;
                                case "12-sec":
                                    if (params != null) {
                                        params.setTariffings(Tariffings.TWELVE_SECONDS);
                                    }
                                    if (tariff != null) {
                                        tariff.setParams(params);
                                    }
                                    break;
                            }
                            break;
                        case "connection-fee":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (params != null) {
                                params.setConnectionFee(Float.parseFloat(xmlEvent.asCharacters().getData()));
                            }
                            if (tariff != null) {
                                tariff.setParams(params);
                            }
                            break;
                        case "Date-connect":
                            xmlEvent = xmlEventReader.nextEvent();
                            if (tariff != null) {
                                tariff.setDateConnect(sdf.parse(xmlEvent.asCharacters().getData()));
                            }
                            break;
                    }

            } if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("tariff")) {
                        tariffs.add(tariff);
                    }
                }
        }
            return tariffs;



    } catch (FileNotFoundException | XMLStreamException | ParseException e) {
            e.printStackTrace();
        }
        return tariffs;

        }


}
