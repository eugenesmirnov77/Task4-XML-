package by.smirnov.taskXML.entity;

import java.util.Objects;

public class Params {

    private int favouriteNum;
    private Tariffings tariffings;
    private float connectionFee;

    public Params() {

    }

    public int getFavouriteNum() {
        return favouriteNum;
    }

    public void setFavouriteNum(int favouriteNum) {
        this.favouriteNum = favouriteNum;
    }

    public Tariffings getTariffings() {
        return tariffings;
    }

    public void setTariffings(Tariffings tariffings) {
        this.tariffings = tariffings;
    }

    public float getConnectionFee() {
        return connectionFee;
    }

    public void setConnectionFee(float connectionFee) {
        this.connectionFee = connectionFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Params params = (Params) o;
        return favouriteNum == params.favouriteNum &&
                Float.compare(params.connectionFee, connectionFee) == 0 &&
                Objects.equals(tariffings, params.tariffings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favouriteNum, tariffings, connectionFee);
    }

    @Override
    public String toString() {
        return " Options - " +
                "Favourite number: " + favouriteNum +
                " Tariffing: " + tariffings +
                " Connection fee: " + connectionFee;
    }
}
