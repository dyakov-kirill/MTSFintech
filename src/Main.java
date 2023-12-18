import services.CreateAnimalService;
import services.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService service = new CreateAnimalServiceImpl();
        service.createAnimals();
        CreateAnimalServiceImpl service2 = new CreateAnimalServiceImpl();
        service2.createAnimals();
        service2.createAnimals(5);
    }
}
