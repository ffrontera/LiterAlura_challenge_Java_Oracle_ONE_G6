package ff.literalura.principal;

import ff.literalura.calculos.CalculosArrays;
import ff.literalura.model.Autor;
import ff.literalura.model.DatosLibro;
import ff.literalura.model.Idioma;
import ff.literalura.model.Libro;
import ff.literalura.repository.AutorRepository;
import ff.literalura.service.BuscarLibro;
import ff.literalura.service.ConsumoAPI;

import java.util.*;
import java.util.stream.Collectors;

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
    CalculosArrays calculos = new CalculosArrays();

    public MenuPrincipal(AutorRepository repository) {
        this.repositorio = repository;
    }

    public void correrPrograma() {
        int opcion;
        boolean salir;
        do {
            mostratMenu();
            opcion = validarOpcion(OPCIONES_MENU.length);
            salir = correrSeleccion(opcion);
        } while (!salir);
        INPUT.close();
    }

    private int validarOpcion(int largo) {
        int opcionSeleccionada;
        do {
            try {
                opcionSeleccionada = Integer.parseInt(INPUT.nextLine());
                if (opcionSeleccionada >= 0 && opcionSeleccionada <= largo)
                    return opcionSeleccionada;
                else
                    System.out.println("Opción ingresada no válida");
            } catch (NumberFormatException e) {
                System.out.println("¡ERROR! La opción ingresada debe ser un número entre 0 y " + largo);
            }
        } while (true);
    }

    public void mostratMenu() {
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
                if (libroBuscado == null)
                    System.out.println("libro no encontrado");
                else
                    buscarLibro(libroBuscado);
                break;
            case 2:
                listarRegistroLibros();
                break;
            case 3:
                listarRegistroAutores();
                break;
            case 4:
                listarAutoresVivosAnio();
                break;
            case 5:
                listarLibrosIdiomas();
                break;
        }
        return salir;
    }

    private void buscarLibro(DatosLibro libroBuscado) {
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
        Optional<Libro> libroBuscadoRegistrado = repositorio.buscarLibro(libro.getTitulo());
        if (libroBuscadoRegistrado.isPresent())
            System.out.println("Libro registrado anteriormente");
        else {
            List<Libro> librosAutor = autor.getLibros();
            librosAutor.add(libro);
            autor.setLibros(librosAutor);
            repositorio.save(autor);
        }
    }


    private void listarRegistroLibros() {
        List<Libro> listadoLibros = repositorio.listarLibrosRegistrados();
        System.out.println();
        listadoLibros.stream().forEach(l -> System.out.println(l +"\n"));
        System.out.println();
    }

    private void listarRegistroAutores() {
        List<Autor> listadoAutores = repositorio.listarAutoresRegistrados();
        System.out.println();
        listadoAutores.forEach(a -> System.out.println(a));
        System.out.println();
    }

    private void listarAutoresVivosAnio() {
        System.out.println("Ingrese el año a buscar");
        int anio = INPUT.nextInt();
        INPUT.nextLine();
        System.out.println();
        Optional<Autor> autorContemporaneoAnio = repositorio.autorVivoAnio(anio);
        if (autorContemporaneoAnio.isPresent())
            autorContemporaneoAnio.stream().forEach(a -> System.out.println(a + "\n"));
        else
            System.out.println("No hay autores contemporenaos al año \"" + anio + "\"");
    }

    private void listarLibrosIdiomas() {
        var idiomaEntrada = INPUT.nextLine();
        var idioma = Idioma.fromString(idiomaEntrada);
        List<Libro> librosIdioma = repositorio.librosIdioma(idioma);
        if (!librosIdioma.isEmpty()) {
            System.out.println("Libros en idioma " + idiomaEntrada);
            librosIdioma.stream().forEach(l -> System.out.println("Libro: " + l.getIdioma() + " Autor: " + l.getAutor() + "\n"));
        } else
            System.out.println("No hay libros registrados en el idioma " + idiomaEntrada);
    }

    private void mostrarMensajeSalir() {
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
        System.out.println(" " + MENSAJE_SALIR + " ");
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
    }

}
