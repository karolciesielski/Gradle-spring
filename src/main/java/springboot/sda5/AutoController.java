package springboot.sda5;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class AutoController {

    List<Auto> auta = new LinkedList<>();

    @PostMapping("/auta")
    public void addAuto(@RequestBody Auto auto) {
        //auta.add(auto);
        System.out.println(auto);
    }

    @GetMapping("/auta")
    public List<Auto> getAuto(@RequestParam(defaultValue = "none") String marka, @RequestParam(defaultValue = "-1") int mocSilnika) {
        if (marka.equals("none") && mocSilnika == -1) return auta;
        else return auta;
    }

    @GetMapping("/auto")
    public Auto getAuto2() {
        return new Auto("Mazda", 1700);
    }


}
