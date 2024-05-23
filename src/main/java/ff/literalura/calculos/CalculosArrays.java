package ff.literalura.calculos;

public class CalculosArrays {

    public int stringMasLargoArray(String[] array) {
        int stringMasLarga = 0;
        for (String valor : array) {
            stringMasLarga = valor.length() > stringMasLarga ? valor.length() : stringMasLarga;
        }
        return stringMasLarga;
    }
}
