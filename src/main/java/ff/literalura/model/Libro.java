package ff.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Libro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") int descargas
) {
}
