package ids.Controller;

import ids.Model.Contest;
import ids.Repository.ContributorRepository;
import ids.Repository.TuristaRepository;
import ids.Servizi.AdminServiziImplementazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TuristaRepository tRep;

    @Autowired
    private ContributorRepository cRep;

    @Autowired
    private AdminServiziImplementazione aSer;

    @GetMapping("/listaContest")
    public ResponseEntity<List<Contest>> listaContest(){
        return aSer.listaContest();
    }

    @PostMapping("/creaContest")
    public void creaContest(@RequestBody Contest contest){
        aSer.creaContest(contest);
    }

    @DeleteMapping("/chiudiContest/{titolo}")
    public void chiudiContest(@PathVariable String titolo){
        aSer.chiudiContest(titolo);
    }

    @PostMapping("modificaRuolo/{e}")
    public void modificaRuolo(@PathVariable String e){
        if(tRep.findById(e).isPresent()){
            aSer.modificaRuolo(tRep.findById(e).get());
        }
        else if(cRep.findById(e).isPresent()){
            aSer.modificaRuolo(cRep.findById(e).get());
        }
    }
}
