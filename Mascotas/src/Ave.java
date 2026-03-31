public class Ave extends Mascota {

    public Ave(int id, String nombre, int edad) {
        super(id, nombre, edad);
    }

    @Override
    public String hacerSonido() {
        return "Pío pío";
    }
}
