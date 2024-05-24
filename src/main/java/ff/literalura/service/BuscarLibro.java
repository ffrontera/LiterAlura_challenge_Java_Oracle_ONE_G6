package ff.literalura.service;

import ff.literalura.model.Datos;
import ff.literalura.model.Libro;

import java.util.Optional;

import static ff.literalura.principal.MenuPrincipal.INPUT;

public class BuscarLibro {
    private ConsumoAPI datos = new ConsumoAPI();
    private ConvertirDatos convertir = new ConvertirDatos();
    private String json;

    public void buscarLibroTitulo(){
        String titulo;
        System.out.println("Ingrese el nombre de libro a buscar:");
        titulo = INPUT.nextLine();
        json = datos.obtenerDatos(titulo);
        var resultadoBusqueda = convertir.obetenerDatos(json, Datos.class);
        Optional<Libro> libroBuscado = resultadoBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro NO encontrado");
        }

    }
}
