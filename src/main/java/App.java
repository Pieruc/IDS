import ids.model.Turista;

public class App {
    public static void main(String[] args) {
        Turista turista = new Turista();
        turista.setNome("Marco");
        turista.setEmail("ggg@gmail");
        turista.setPassword("pippo");

        TuristaRepository repository= new TuristaRepository();

        repository.addTurista(turista);

        System.out.println("Aggiunto: "+ turista.toString());

        turista = repository.findTurista(turista.getId());

        System.out.println("Trovato: "+ turista.toString());

        turista.setNome("Giulia");

        repository.update(turista);

        System.out.println("Aggiornato: "+turista.toString());

        repository.delete(turista);

        System.out.println("Cancellato: "+turista.toString());
    }
}
