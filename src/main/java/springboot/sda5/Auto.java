package springboot.sda5;

public class Auto {
    private String marka;
    private int mocSilnika;

    public Auto(String marka, int mocSilnika) {
        this.marka = marka;
        this.mocSilnika = mocSilnika;

    }

    public String getMarka() {
        return marka;
    }

    public int getMocSilnika() {
        return mocSilnika;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setMocSilnika(int mocSilnika) {
        this.mocSilnika = mocSilnika;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "marka='" + marka + '\'' +
                ", mocSilnika=" + mocSilnika +
                '}';
    }
}
