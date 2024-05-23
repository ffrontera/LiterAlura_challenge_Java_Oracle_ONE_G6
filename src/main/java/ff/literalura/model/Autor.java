package ff.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") int anioNacimiento,
        @JsonAlias("death_year") int anioMuerte
) {
}
