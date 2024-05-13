import ids.model.*;

public class App {
    public static void main(String[] args) {
        /*Turista turista = new Turista();
        turista.setNome("Marco");
        turista.setEmail("ggg@gmail");
        turista.setPassword("pippo");*/

        UtenteFactory factory = new UtenteFactory();

        Utente user1 = factory.getUtente("Turista","Martina","ggg@email","pippo");
        user1.crea();

        if(user1 instanceof Turista turista){
            TuristaRepository tRepository = new TuristaRepository();

            tRepository.addTurista(turista);

            System.out.println("Aggiunto: "+ turista.toString());

            turista = tRepository.findTurista(turista.getId());

            System.out.println("Trovato: "+ turista.toString());

            turista.setNome("Giulia");

            /*tRepository.update(turista);

            System.out.println("Aggiornato: "+turista.toString());

            tRepository.delete(turista);

            System.out.println("Cancellato: "+turista.toString());*/
        }



        /*Contributor contributor = new Contributor();
        contributor.setNome("Luca");
        contributor.setEmail("ggg@gmail");
        contributor.setPassword("pippo");

        ContributorAutorizzatoRepository cRepository = new ContributorAutorizzatoRepository();

        cRepository.addContributorAutorizzato(contributor);

        System.out.println("Aggiunto: "+ contributor.toString());

        contributor = cRepository.findContributor(contributor.getId());

        System.out.println("Trovato: "+ contributor.toString());

        contributor.setNome("Gianni");

        cRepository.update(contributor);

        System.out.println("Aggiornato: "+contributor.toString());

        cRepository.delete(contributor);

        System.out.println("Cancellato: "+contributor.toString());*/
    }
}
