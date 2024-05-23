package ff.literalura;

import ff.literalura.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ConcurrentModificationException;

@SpringBootApplication
public class LiterAluraChallengeJavaOracleOneG6Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeJavaOracleOneG6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI datos = new ConsumoAPI();
		System.out.println(datos.obtenerDatos(""));
	}
}
