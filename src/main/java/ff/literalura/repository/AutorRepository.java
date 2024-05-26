package ff.literalura.repository;

import ff.literalura.model.Autor;
import ff.literalura.model.Idioma;
import ff.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.titulo LIKE %:nombre%")
    Optional<Libro> buscarLibro(String nombre);

    @Query("SELECT l FROM Autor a JOIN a.libros l")
    List<Libro> listarLibrosRegistrados();

    @Query("SELECT a FROM Autor a")
    List<Autor> listarAutoresRegistrados();

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND a.anioMuerte >= :anio")
    Optional<Autor> autorVivoAnio(int anio);

    @Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.idioma = :i")
    List<Libro> librosIdioma(Idioma i);
}
