package ids.Controller;

import ids.Model.*;
import ids.Repository.ContestRepository;
import ids.Repository.ContributorRepository;
import ids.Servizi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contributor")
public class ContributorController {

    @Autowired
    private UtenteServiziImplementazione uSer;

    @Autowired
    private MappaServiziImplementazione mSer;

    @Autowired
    private ContributorRepository cRep;

    @Autowired
    private ContestRepository coRep;

    @GetMapping("/")
    public String home(){
        return "homeContributor";
    }

    @GetMapping("/map")
    public String map(Model model) {
        return "mapContributor";
    }

    @GetMapping("/getAllContenuti")
    @ResponseBody
    public List<Contenuto> getAllContenuto() {
        return mSer.getAllContenuti();
    }

    @GetMapping("/contest")
    public String contest(Model model){
        model.addAttribute("contests", coRep.findAll());
        return "contest";
    }

    @PostMapping("/contest")
    public String contest(@RequestParam String titolo, @RequestParam String email){
        if(cRep.findById(email).isPresent()){
            Contributor temp = cRep.findById(email).get();
        }
        uSer.partecipaContest(titolo, email);
        return "redirect:/turista/";
    }

}