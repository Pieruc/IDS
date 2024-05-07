import ids.model.Contributor;
import ids.model.Turista;

public class App {
    public static void main(String[] args) {
        Turista turista = new Turista();
        turista.setNome("Marco");
        turista.setEmail("ggg@gmail");
        turista.setPassword("pippo");

        Repository repository = new Repository();

        repository.add(turista);

        System.out.println("Aggiunto: "+ turista.toString());

        turista = (Turista) repository.find(turista.getId());

        System.out.println("Trovato: "+ turista.toString());

        turista.setNome("Giulia");

        repository.update(turista);

        System.out.println("Aggiornato: "+turista.toString());

        /*repository.delete(turista);

        System.out.println("Cancellato: "+turista.toString());*/

        Contributor contributor = new Contributor();
        contributor.setNome("Luca");
        contributor.setEmail("ggg@gmail");
        contributor.setPassword("pippo");

        Repository c_repository = new Repository();

        c_repository.add(contributor);

        System.out.println("Aggiunto: "+ contributor.toString());

        contributor = (Contributor) c_repository.find(contributor.getId());

        System.out.println("Trovato: "+ contributor.toString());

        contributor.setNome("Gianni");

        c_repository.update(contributor);

        System.out.println("Aggiornato: "+contributor.toString());

        /*c_repository.delete(contributor);

        System.out.println("Cancellato: "+contributor.toString());*/
    }
}
