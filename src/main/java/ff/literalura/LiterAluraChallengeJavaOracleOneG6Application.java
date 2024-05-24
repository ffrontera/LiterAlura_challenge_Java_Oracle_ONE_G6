package ff.literalura;

import ff.literalura.principal.MenuPrincipal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ConcurrentModificationException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LiterAluraChallengeJavaOracleOneG6Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeJavaOracleOneG6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal aplicacion = new MenuPrincipal();
		aplicacion.correrPrograma();
	}
}
