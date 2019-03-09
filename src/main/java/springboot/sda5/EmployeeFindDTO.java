package springboot.sda5;

import javax.validation.constraints.*;

public class EmployeeFindDTO {

    @NotNull
//    @Size(min=3)
    private String imie;

    @NotNull
//    @Size(min=3)
    private String nazwisko;

    @NotNull
//    @Min(1700)
//    @Max(4000)
    private float zarobki;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public float getZarobki() {
        return zarobki;
    }

    public void setZarobki(int zarobki) {
        this.zarobki = zarobki;
    }
}
