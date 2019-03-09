package springboot.sda5;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class EmployeeAddDTO {
    @NotEmpty
    @Size(min=3)
    private String imie;

    @NotEmpty
    @Size(min=3)
    private String nazwisko;

    @Min(1700)
    private int zarobki;

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

    public int getZarobki() {
        return zarobki;
    }

    public void setZarobki(int zarobki) {
        this.zarobki = zarobki;
    }
}
