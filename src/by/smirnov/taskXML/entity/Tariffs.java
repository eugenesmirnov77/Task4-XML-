package by.smirnov.taskXML.entity;

import java.util.Date;

public class Tariffs {

    private String name;
    private Operator operator;
    private float payroll;
    private CallPrice callPrice;
    private float smsPrice;
    private Params params;
    private Date dateConnect;
    private Tariffings tariffings;

    public Tariffs() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public float getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(float smsPrice) {
        this.smsPrice = smsPrice;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Date getDateConnect() {
        return dateConnect;
    }

    public void setDateConnect(Date dateConnect) {
        this.dateConnect = dateConnect;
    }

    public Tariffings getTariffings() {
        return tariffings;
    }

    public void setTariffings(Tariffings tariffings) {
        this.tariffings = tariffings;
    }

    @Override
    public String toString() {
        return "Tariff - " + name + ":" +
                " Details (Operator: " + operator +
                " Payroll: " + payroll +
                callPrice +
                " SMS price: " + smsPrice +
                params +
                " Date of connect: " + dateConnect + ")";
    }
}
