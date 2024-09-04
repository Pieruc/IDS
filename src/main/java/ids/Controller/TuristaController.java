package ids.Controller;

import ids.Model.*;
import ids.Repository.ContestRepository;
import ids.Repository.TuristaRepository;
import ids.Servizi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/turista")
public class TuristaController {

    @Autowired
    private UtenteServiziImplementazione uSer;

    @Autowired
    private MappaServiziImplementazione mSer;

    @Autowired
    private TuristaRepository tRep;

    @Autowired
    private ContestRepository cRep;

    @GetMapping("/")
    public String home(){
        return "homeTurista";
    }

    @GetMapping("/map")
    public String map(Model model) {
        return "mapTurista";
    }

    @GetMapping("/getAllContenuti")
    @ResponseBody
    public List<Contenuto> getAllContenuto() {
        return mSer.getAllContenuti();
    }

    @GetMapping("/contest")
    public String contest(Model model){
        model.addAttribute("contests", cRep.findAll());
        return "contest";
    }

    @PostMapping("/contest")
    public String contest(@RequestParam String titolo, @RequestParam String email){
        if(tRep.findById(email).isPresent()){
            Turista temp = tRep.findById(email).get();
        }
        uSer.partecipaContest(titolo, email);
        return "redirect:/turista/";
    }

}
