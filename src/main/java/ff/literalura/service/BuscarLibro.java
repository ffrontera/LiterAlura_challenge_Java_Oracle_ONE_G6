package ff.literalura.service;

import static ff.literalura.principal.MenuPrincipal.INPUT;

public class BuscarLibro {
    private ConsumoAPI datos = new ConsumoAPI();

    public void buscarLibroTitulo(){
        String titulo;
        System.out.println("Ingrese el nombre de libro a buscar:");
        titulo = INPUT.nextLine();
        System.out.println(datos.obtenerDatos(titulo));
    }
}
