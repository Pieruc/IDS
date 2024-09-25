package ids.Controller;

import ids.Model.*;
import ids.Repository.ContributorRepository;
import ids.Repository.TuristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ids.Servizi.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UtenteServiziImplementazione uSer;

    @Autowired
    private AdminServiziImplementazione aSer;

    @Autowired
    private TuristaRepository tRep;

    @Autowired
    private ContributorRepository cRep;

    @GetMapping("/")
    public String admin() {
        return "admin";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }

    @GetMapping("/listaTuristi")
    public String listaTuristi(Model model) {
        List<Turista> turisti = uSer.listaTuristi();
        model.addAttribute("turisti", turisti);
        return "listaTuristi";
    }

    @GetMapping("/listaTuristiAutorizzati")
    public String listaTuristiAutorizzati(Model model) {
        List<TuristaAutorizzato> turistiAutorizzati = uSer.listaTuristiAutorizzati();
        model.addAttribute("turistiAutorizzati", turistiAutorizzati);
        return "listaTuristiAutorizzati";
    }

    @GetMapping("/listaContributor")
    public String listaContributor(Model model) {
        List<Contributor> contributor = uSer.listaContributor();
        model.addAttribute("contributor", contributor);
        return "listaContributor";
    }

    @GetMapping("/listaContributorAutorizzati")
    public String listaContributorAutorizzati(Model model) {
        List<ContributorAutorizzato> contributorAutorizzati = uSer.listaContributorAutorizzati();
        model.addAttribute("contributorAutorizzati", contributorAutorizzati);
        return "listaContributorAutorizzati";
    }

    @GetMapping("/listaContest")
    public String listaContest(Model model) {
        List<Contest> contest = aSer.listaContest();
        model.addAttribute("contest", contest);
        return "listaContest";
    }

    @GetMapping("/modificaRuolo")
    public String modificaRuolo(){
        return "modificaRuolo";
    }

    @PostMapping("/modificaRuolo")
    public String modificaRuolo(@RequestParam String email, Model model){
        if (tRep.findById(email).isPresent()){
            aSer.modificaRuolo(tRep.findById(email).get());
            model.addAttribute("message", "Ruolo modificato correttamente");
        }
        else if (cRep.findById(email).isPresent()){
            aSer.modificaRuolo(cRep.findById(email).get());
            model.addAttribute("message", "Ruolo modificato correttamente");
        }
        else {
            model.addAttribute("message", "L'utente non esiste");
            return "admin/modificaRuolo";
        }
        return "redirect:/admin/";
    }

    @GetMapping("/creaContest")
    public String creaContest(){
        return "creaContest";
    }

    @PostMapping("/creaContest")
    public String creaContest(@RequestParam String titolo, @RequestParam(name = "esclusivita", defaultValue = "false") boolean esclusivita,
                              @RequestParam String descrizione, Model model) {
        Contest contest = new Contest(titolo, esclusivita, descrizione);
        aSer.creaContest(contest);
        return "redirect:/admin/";
    }

    @PostMapping("/chiudiContest")
    public String chiudiContest(@RequestParam String titolo){
        aSer.chiudiContest(titolo);
        return "redirect:/admin/";
    }

    @GetMapping("listaSegnalazioni")
    public String listaSegnalazioni(Model model){
        List<Segnalazione> segnalazioni = uSer.listaSegnalazioni();
        model.addAttribute("segnalazioni", segnalazioni);
        return "/listaSegnalazioni";
    }

    @PostMapping("/verificaSegnalazione")
    public String verificaSegnalazione(@RequestParam String titolo){
        return "redirect:/map";
    }

    @GetMapping("/listaPartecipanti")
    public String listaPartecipanti(@RequestParam String titolo, Model model){

        List<Utente> partecipanti = aSer.getPartecipantiByTitolo(titolo);
        model.addAttribute("titolo", titolo);
        model.addAttribute("utenti", partecipanti);
        return "listaPartecipanti";
    }

    @GetMapping("selezionaVincitore")
    public String selezionaVincitore(){
        return "redirect:/admin/";
    }

}
