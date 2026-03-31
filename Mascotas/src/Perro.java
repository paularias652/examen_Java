public class Perro extends Mascota {

    public Perro(int id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    @Override
    public String hacerSonido() {
        return "Guau guau";
    }
}