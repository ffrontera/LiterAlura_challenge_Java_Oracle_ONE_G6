package ff.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Datos(
        @JsonAlias("results") List<Libro> libros
) {
}
