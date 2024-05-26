package ff.literalura;

import ff.literalura.principal.MenuPrincipal;
import ff.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallengeJavaOracleOneG6Application implements CommandLineRunner {

	@Autowired
	AutorRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeJavaOracleOneG6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal aplicacion = new MenuPrincipal(repository);
		aplicacion.correrPrograma();
	}
}
