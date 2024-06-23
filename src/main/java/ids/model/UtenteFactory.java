package ids.Model;

public class UtenteFactory {

    public Utente getUtente(String tipoUtente, String n, String e, String p){
        if(tipoUtente==null){
            return null;
        }
        if(tipoUtente.equalsIgnoreCase("Turista")){
            return new Turista(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Turista autorizzato")){
            return new TuristaAutorizzato(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Contributor")){
            return new Contributor(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Contributor autorizzato")){
            return new ContributorAutorizzato(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Animatore")){
            return new Animatore(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Curatore")){
            return new Curatore(n,e,p);
        }
        else if(tipoUtente.equalsIgnoreCase("Gestore")){
            return new Gestore(n,e,p);
        }
        return null;
    }
}
