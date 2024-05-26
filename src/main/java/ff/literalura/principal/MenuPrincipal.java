package ff.literalura.principal;

import ff.literalura.calculos.CalculosArrays;
import ff.literalura.model.Autor;
import ff.literalura.model.DatosLibro;
import ff.literalura.model.Libro;
import ff.literalura.repository.AutorRepository;
import ff.literalura.service.BuscarLibro;
import ff.literalura.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPrincipal {
    private final String[] OPCIONES_MENU = {
            "Buscar Libro por titulo",
            "Listar libros registrados",
            "Listar autores registrados",
            "Listar autores vivos en un determinado año",
            "Listar libros por idioma",
            "Salir"};
    private final String SEPARADOR_HORIZONTAL = "-";
    private final String MENSAJE_SALIR = "Gracias por ser parte de LiterAlura";
    static public final Scanner INPUT = new Scanner(System.in);
    private AutorRepository repositorio;
    private DatosLibro libroBuscado;
    private BuscarLibro busqueda = new BuscarLibro();

    public MenuPrincipal(AutorRepository repository) {
        this.repositorio = repository;
    }

    public void correrPrograma() {
        int opcion;
        boolean salir;
        do {
            mostratMenu();
            opcion = validarOpcion();
            salir = correrSeleccion(opcion);
        } while (!salir);
        INPUT.close();
    }

    private int validarOpcion() {
        int opcionSeleccionada = -1;
        boolean continuar = false;
        while (!continuar){
            opcionSeleccionada = INPUT.nextInt();
            INPUT.nextLine();
            if (opcionSeleccionada >= 0 || opcionSeleccionada <= OPCIONES_MENU.length)
                continuar = true;
            else
                System.out.println("Opción ingresada no válida");
        }
        return opcionSeleccionada;
    }

    public void mostratMenu() {
        CalculosArrays calculos = new CalculosArrays();
        int anchoMenu = 9 + calculos.stringMasLargoArray(OPCIONES_MENU);
        int numeroOpcion;
        mostrarLineaVetical(anchoMenu);
        for (int i = 0; i < OPCIONES_MENU.length; i++ ){
            numeroOpcion = i == OPCIONES_MENU.length - 1 ? 0 : i + 1;
            if (i == OPCIONES_MENU.length - 1)
                System.out.println();
            System.out.println(" " + (numeroOpcion) + " - " + OPCIONES_MENU[i]);
        }
        mostrarLineaVetical(anchoMenu);
    }

    private void mostrarLineaVetical(int anchoLinea) {
        for (int i = 0; i < anchoLinea; i++) {
            System.out.print(SEPARADOR_HORIZONTAL);
        }
        System.out.println();
    }

    private boolean correrSeleccion(int opcion) {
        boolean salir = false;
        switch (opcion){
            case 0:
                mostrarMensajeSalir();
                salir = true;
                break;
            case 1:
                libroBuscado = busqueda.buscarLibroTitulo();
                Autor autor = new Autor(libroBuscado);
                Optional<Autor> buscarAutor = repositorio.findByNombreContainsIgnoreCase(autor.getNombre());
                if (buscarAutor.isPresent())
                    autor = buscarAutor.get();
                else {
                    autor.setLibros(new ArrayList<>());
                    repositorio.save(autor);
                }
                Libro libro = new Libro(libroBuscado);
                libro.setAutor(autor);
                List<Libro> librosAutor = autor.getLibros();
                librosAutor.add(libro);
                autor.setLibros(librosAutor);
                repositorio.save(autor);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return salir;
    }

    private void mostrarMensajeSalir() {
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
        System.out.println(" " + MENSAJE_SALIR + " ");
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
    }

}
