package ff.literalura.principal;

import ff.literalura.calculos.CalculosArrays;
import ff.literalura.service.BuscarLibro;

import java.util.Scanner;

public class MenuPrincipal {
    private final String[] OPCIONES_MENU = {"Buscar Libro por titulo", "Salir"};
    private final String SEPARADOR_HORIZONTAL = "-";
    private final String MENSAJE_SALIR = "Gracias por ser parte de LiterAlura";
    static public final Scanner INPUT = new Scanner(System.in);
    private BuscarLibro libro = new BuscarLibro();

    public void correrPrograma() {
        int opcion;
        boolean salir;
        do {
            mostratMenu();
            opcion = INPUT.nextInt();
            INPUT.nextLine();
            salir = correrSeleccion(opcion);
        } while (!salir);
        INPUT.close();
    }

    public void mostratMenu(){
        CalculosArrays calculos = new CalculosArrays();
        int anchoMenu = 9 + calculos.stringMasLargoArray(OPCIONES_MENU);
        int numeroOpcion;
        for (int i = 0; i < OPCIONES_MENU.length; i++ ){
            numeroOpcion = i == OPCIONES_MENU.length - 1 ? 0 : i + 1;
            if (i == 0)
                mostrarLineaVetical(anchoMenu);
            System.out.println(" " + (numeroOpcion) + " - " + OPCIONES_MENU[i]);
            if (i == OPCIONES_MENU.length - 1)
                mostrarLineaVetical(anchoMenu);
        }
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
            case 1:
                libro.buscarLibroTitulo();
                break;
            case 0:
                mostrarMensajeSalir();
                salir = true;
        }
        return salir;
    }

    private void mostrarMensajeSalir() {
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
        System.out.println(" " + MENSAJE_SALIR + " ");
        mostrarLineaVetical(MENSAJE_SALIR.length() + 2);
    }

}
