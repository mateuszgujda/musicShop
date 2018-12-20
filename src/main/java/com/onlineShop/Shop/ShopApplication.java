package com.onlineShop.Shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa uruchamiająca aplikacje
 */
@SpringBootApplication
public class ShopApplication {

	/**
	 * Start aplikacji spring {@link SpringApplication}
	 * @param args argumenty konsolowe
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
}
