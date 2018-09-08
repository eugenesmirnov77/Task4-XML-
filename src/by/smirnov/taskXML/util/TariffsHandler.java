package by.smirnov.taskXML.util;

import by.smirnov.taskXML.entity.CallPrice;
import by.smirnov.taskXML.entity.Params;
import by.smirnov.taskXML.entity.Tariffings;
import by.smirnov.taskXML.entity.Tariffs;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static by.smirnov.taskXML.entity.Operator.LIFE;
import static by.smirnov.taskXML.entity.Operator.MTS;
import static by.smirnov.taskXML.entity.Operator.VELCOM;

public class TariffsHandler extends DefaultHandler {

    private List<Tariffs> tariffs;
    private Tariffs current;
    private CallPrice callPrice;
    private Params params;
    private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

    public TariffsHandler() {
    }

    public List<Tariffs> getTariffs() {
        return tariffs;
    }

    private boolean bName = false;
    private boolean bPayroll = false;
    private boolean bOperator = false;
    private boolean bInternal = false;
    private boolean bExternal = false;
    private boolean bFixed = false;
    private boolean bDate = false;
    private boolean bSMS = false;
    private boolean bFavourite = false;
    private boolean bFee = false;
    private boolean bTariffing = false;


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        if (qName.equalsIgnoreCase("tariff")) {
            current = new Tariffs();
            if (tariffs == null) {
                tariffs = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("parameters")) {
            params = new Params();
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("payroll")) {
            bPayroll = true;
        } else if (qName.equalsIgnoreCase("operator")) {
            bOperator = true;
        } else if (qName.equalsIgnoreCase("call-prices")) {
            callPrice = new CallPrice();
        } else if (qName.equalsIgnoreCase("internal")) {
            bInternal = true;
        } else if (qName.equalsIgnoreCase("external")) {
            bExternal = true;
        } else if (qName.equalsIgnoreCase("fixed-phones")) {
            bFixed = true;
        } else if (qName.equalsIgnoreCase("Date-connect")) {
            bDate = true;
        } else if (qName.equalsIgnoreCase("SMS-price")) {
            bSMS = true;
        } else if (qName.equalsIgnoreCase("favourite-number")) {
            bFavourite = true;
        } else if (qName.equalsIgnoreCase("connection-fee")) {
            bFee = true;
        } else if (qName.equalsIgnoreCase("tariffing")) {
            bTariffing = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String findString = new String(ch, start, length).trim();
        if (bName) {
            current.setName(String.valueOf(findString));
            bName = false;
        } else if (bPayroll) {
            current.setPayroll(Float.parseFloat(findString));
            bPayroll = false;
        } else if (bOperator) {
            switch (String.valueOf(findString)) {
                case "MTS":
                    current.setOperator(MTS);
                    bOperator = false;
                    break;
                case "Velcom":
                    current.setOperator(VELCOM);
                    bOperator = false;
                    break;
                case "Life":
                    current.setOperator(LIFE);
                    bOperator = false;
                    break;
            }
        } else if (bInternal) {
            callPrice.setInternal(Float.parseFloat(findString));
            current.setCallPrice(callPrice);
            bInternal = false;
        } else if (bExternal) {
            callPrice.setExternal(Float.parseFloat(findString));
            current.setCallPrice(callPrice);
            bExternal = false;
        } else if (bFixed) {
            callPrice.setFixedPhones(Float.parseFloat(findString));
            current.setCallPrice(callPrice);
            bFixed = false;
        } else if (bDate) {
            try {
                current.setDateConnect(sdf.parse(findString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bDate = false;
        } else if (bSMS) {
            current.setSmsPrice(Float.parseFloat(findString));
            bSMS = false;
        } else if (bFavourite) {
            params.setFavouriteNum(Integer.parseInt(findString));
            current.setParams(params);
            bFavourite = false;
        } else if (bFee) {
            params.setConnectionFee(Float.parseFloat(findString));
            current.setParams(params);
            bFee = false;
        } else if (bTariffing) {
            switch (String.valueOf(findString)) {
                case "1-min":
                    params.setTariffings(Tariffings.ONE_MINUTE);
                    current.setParams(params);
                    bTariffing = false;
                    break;
                case "12-sec":
                    params.setTariffings(Tariffings.TWELVE_SECONDS);
                    current.setParams(params);
                    bTariffing = false;
                    break;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("tariff")) {
            tariffs.add(current);
        }

    }
}
