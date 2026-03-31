public class Gato extends Mascota {

    public Gato(int id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    @Override
    public String hacerSonido() {
        return "Miau";
    }
}