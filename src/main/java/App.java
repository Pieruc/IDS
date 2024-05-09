import ids.model.Contributor;
import ids.model.Turista;

public class App {
    public static void main(String[] args) {
        Turista turista = new Turista();
        turista.setNome("Marco");
        turista.setEmail("ggg@gmail");
        turista.setPassword("pippo");

        TuristaRepository tRepository = new TuristaRepository();

        tRepository.addTurista(turista);

        System.out.println("Aggiunto: "+ turista.toString());

        turista = tRepository.findTurista(turista.getId());

        System.out.println("Trovato: "+ turista.toString());

        turista.setNome("Giulia");

        tRepository.update(turista);

        System.out.println("Aggiornato: "+turista.toString());

        tRepository.delete(turista);

        System.out.println("Cancellato: "+turista.toString());

        Contributor contributor = new Contributor();
        contributor.setNome("Luca");
        contributor.setEmail("ggg@gmail");
        contributor.setPassword("pippo");

        ContributorRepository cRepository = new ContributorRepository();

        cRepository.addContributor(contributor);

        System.out.println("Aggiunto: "+ contributor.toString());

        contributor = (Contributor) cRepository.findContributor(contributor.getId());

        System.out.println("Trovato: "+ contributor.toString());

        contributor.setNome("Gianni");

        cRepository.update(contributor);

        System.out.println("Aggiornato: "+contributor.toString());

        cRepository.delete(contributor);

        System.out.println("Cancellato: "+contributor.toString());
    }
}
