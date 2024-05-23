package ff.literalura.principal;

import ff.literalura.calculos.CalculosArrays;
import ff.literalura.service.BuscarLibro;
import ff.literalura.service.ConsumoAPI;

import java.util.Scanner;

public class MenuPrincipal {
    private final String[] OPCIONES_MENU = {"Buscar Libro por titulo"};
    private final String SEPARADOR_HORIZONTAL = "-";
    static public final Scanner INPUT = new Scanner(System.in);
    private BuscarLibro libro = new BuscarLibro();

    public void correrPrograma() {
        int opcion;
        mostratMenu();
        opcion = INPUT.nextInt();
        INPUT.nextLine();
        correrSeleccion(opcion);
        INPUT.close();
    }

    public void mostratMenu(){
        CalculosArrays calculos = new CalculosArrays();
        int anchoMenu = 9 + calculos.stringMasLargoArray(OPCIONES_MENU);
        for (int i = 0; i < OPCIONES_MENU.length; i++ ){
            if (i == 0)
                mostrarLineaVetical(anchoMenu);
            System.out.println(" " + (i + 1) + " - " + OPCIONES_MENU[i]);
            mostrarLineaVetical(anchoMenu);
        }
    }

    private void mostrarLineaVetical(int anchoLinea) {
        for (int i = 0; i < anchoLinea; i++) {
            System.out.print(SEPARADOR_HORIZONTAL);
        }
        System.out.println();
    }

    private void correrSeleccion(int opcion) {
        switch (opcion){
            case 1:
                libro.buscarLibroTitulo();
                break;
        }
    }

}
