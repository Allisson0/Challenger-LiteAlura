package br.com.litealura;

import br.com.litealura.Main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.menu();
    }
}
