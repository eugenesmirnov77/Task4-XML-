package by.smirnov.taskXML.entity;

public class CallPrice {

    private float internal;
    private float external;
    private float fixedPhones;

    public CallPrice() {

    }

    public float getInternal() {
        return internal;
    }

    public void setInternal(float internal) {
        this.internal = internal;
    }

    public float getExternal() {
        return external;
    }

    public void setExternal(float external) {
        this.external = external;
    }

    public float getFixedPhones() {
        return fixedPhones;
    }

    public void setFixedPhones(float fixedPhones) {
        this.fixedPhones = fixedPhones;
    }

    @Override
    public String toString() {
        return " Call Price - " +
                "Internal calls: " + internal +
                " External calls: " + external +
                " Calls on fixed phones: " + fixedPhones;
    }
}
