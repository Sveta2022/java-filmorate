package ru.yandex.practicum.javafilmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication 
public class JavaFilmorateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaFilmorateApplication.class, args);
	}

}
