package ids.Controller;

import ids.Model.*;
import ids.Repository.*;
import ids.Servizi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UtenteServiziImplementazione uSer;

    @Autowired
    private MappaServiziImplementazione mSer;

    @Autowired
    private TuristaRepository tRep;

    @Autowired
    private ContributorRepository cRep;

    @Autowired
    private ContenutoRepository mRep;

    @Autowired
    private SegnalazioneRepository sRep;

    @GetMapping("/")
    public String firstPage() {
        return "firstPage";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        if(tRep.findById(email).isPresent()){
            if(tRep.findById(email).get().getPassword().equals(password)){
                return "redirect:/turista/";
            }
        }
        if(cRep.findById(email).isPresent()){
            if(cRep.findById(email).get().getPassword().equals(password)){
                return "redirect:/contributor/";
            }
        }
        else model.addAttribute("message", "Email o password incorrete");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String submitForm(@RequestParam String tipo, @RequestParam String name, @RequestParam String email, @RequestParam String password, Model model) {
        if (tipo.equals("Turista")) {
            if(cRep.findById(email).isPresent()||tRep.findById(email).isPresent()){
                model.addAttribute("message", "Email già in uso");
                return "register";
            }
            Turista user = new Turista();
            user.setNome(name);
            user.setEmail(email);
            user.setPassword(password);
            uSer.save(user);
            return "redirect:/turista/";
        }
        else if (tipo.equals("Contributor")) {
            if(cRep.findById(email).isPresent()||tRep.findById(email).isPresent()){
                model.addAttribute("message", "Email già in uso");
                return "register";
            }
            Contributor user = new Contributor();
            user.setNome(name);
            user.setEmail(email);
            user.setPassword(password);
            uSer.save(user);
            return "redirect:/contributor/";
        }
        model.addAttribute("message", "User registered successfully");
        return "redirect:/";
    }

    @GetMapping("/map")
    public String map(Model model) {
        return "map.html";
    }

    @GetMapping("/getAllContenuti")
    @ResponseBody
    public List<Contenuto> getAllContenuto() {
        return mSer.getAllContenuti();
    }

    @PostMapping("/aggiungiContenuto")
    public String aggiungiContenuto(@RequestParam String title, @RequestParam String description,
                                    @RequestParam double latitude, @RequestParam double longitude, @RequestParam String imageUrl, Model model) {
        mSer.aggiungiContenuto(latitude, longitude, title, description, imageUrl);
        model.addAttribute("title", title);
        return "map.html";
    }

    @PostMapping("/deleteContenuto")
    @ResponseBody
    public void deleteContenuto(@RequestParam String titolo) {
        mSer.eliminaContenuto(mSer.trovaContenutoConTitolo(titolo));
    }

    @GetMapping("/segnalazione")
    public String segnalazione(){
        return "segnalazione.html";
    }

    @PostMapping("/segnalaContenuto")
    public String segnalaContenuto(@RequestParam String titolo, @RequestParam String email, @RequestParam String luogo,
                                   @RequestParam String messaggio) {
        Segnalazione s = new Segnalazione(titolo, uSer.trovaTuristaConMail(email), mSer.trovaContenutoConTitolo(luogo), messaggio);
        sRep.save(s);
        return "redirect:/map";
    }

    @GetMapping("/creaItinerario")
    public String creItinerario(Model model){
        List<Contenuto> luoghi = mRep.findAll();
        model.addAttribute("luoghi", luoghi);
        return "creaItinerario";
    }

    @PostMapping("/creaItinerario")
    public String creaItinerario(@RequestParam List<Contenuto> luoghi, @RequestParam String nome,
                                 @RequestParam String email, Model model){
        if(tRep.findById(email).isPresent()){
            uSer.creaItinerario(tRep.findById(email).get(), nome, luoghi);
            return "redirect:/turista/map";
        }
        else {
            model.addAttribute("message", "Email non esistene");
            return "creaItinerario";
        }
    }

    @GetMapping("cancellaAccount")
    public String cancellaAccount(){
        return "/cancellaAccount";
    }

    @PostMapping("cancellaAccount")
    public String cancellaAccount(@RequestParam String email){
        uSer.eliminaAccount(email);
        return "redirect:/login";
    }
}
