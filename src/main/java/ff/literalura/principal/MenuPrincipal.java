package ff.literalura.principal;

import ff.literalura.calculos.CalculosArrays;

import java.util.Scanner;

public class MenuPrincipal {
    private final String[] OPCIONES_MENU = {"Buscar Libro por titulo"};
    public final Scanner INPUT = new Scanner(System.in);

    public void mostratMenu(){
        CalculosArrays calculos = new CalculosArrays();
        int anchoMenu = 9 + calculos.stringMasLargoArray(OPCIONES_MENU);
    }
    
}
