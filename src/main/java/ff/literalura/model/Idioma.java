package ff.literalura.model;

public enum Idioma {
    ES("es"),
    FR("fr"),
    PT("pt"),
    EN("en");

    private String idioma;

    Idioma(String idioma) {
        this.idioma = idioma;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idioma.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
