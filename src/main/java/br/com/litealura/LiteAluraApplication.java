package br.com.litealura;

import br.com.litealura.Main.Main;
import br.com.litealura.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteAluraApplication implements CommandLineRunner {

    @Autowired
    private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(repository);
        main.menu();
    }
}
