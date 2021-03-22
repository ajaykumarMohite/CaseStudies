package com.cognizant.moviecruiser;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.service.MovieService;
import com.cognizant.moviecruiser.util.DateUtil;

@SpringBootApplication
public class MoviecruiserApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(MoviecruiserApplication.class);
	private static MovieService movieService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MoviecruiserApplication.class, args);
		movieService = context.getBean(MovieService.class);
		LOGGER.info("Inside main");
		Scanner sc = new Scanner(System.in);
		String choice;

		do {
			System.out.println("Menu");
			System.out.println("****************************************");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.println("****************************************");

			choice = sc.nextLine();
			System.out.println("****************************************");

			switch (choice) {
			case "1": {
				String adminChoice;
				do {
					System.out.println("Admin Menu");
					System.out.println("****************************************");
					System.out.println("1. Get Movie List");
					System.out.println("2. Modify Movie");
					System.out.println("3. Get Movie");
					System.out.println("4. Main Menu");
					System.out.println("****************************************");

					adminChoice = sc.nextLine();
					System.out.println("****************************************");

					switch (adminChoice) {
					case "1": {
						System.out.println("Admin Movie List");
						System.out.println("****************************************");
						testGetMovieListAdmin();
						System.out.println("****************************************");
						break;
					}
					case "2": {
						testModifyMovie();
						System.out.println("Movie 1 is modified !! Enter 3 to display the changes.");
						System.out.println("****************************************");
						break;
					}
					case "3": {
						System.out.println("Movie 1 is displayed !!");
						System.out.println("****************************************");
						testGetMovie(1); // Since movie 1 is modified details of movie id->1 is retrieving
						System.out.println("****************************************");
						break;
					}
					case "4": {
						break;
					}
					default: {
						System.out.println("Enter valid choice");
						System.out.println("****************************************");
					}
					}
				} while (!adminChoice.equals("4"));
				System.out.println("****Thank YOU****");
				break;
			}
			case "2": {
				System.out.println("Customer Movie List");
				System.out.println("****************************************");
				testGetMovieListCustomer();
				System.out.println("****************************************");
				break;
			}
			case "3": {
				break;
			}
			default: {
				System.out.println("Enter valid choice");
				System.out.println("****************************************");
			}
			}
		} while (!choice.equals("3"));
		System.out.println("****Thank YOU****");
		sc.close();

	}

	private static void testGetMovieListAdmin() {
		LOGGER.info("Start");
		List<Movie> list = movieService.getMenuListAdmin();
		System.out.println(String.format("%-3s %-20s %-15s %-8s %-30s %-18s " + "%-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		System.out.println(
				"____________________________________________________________________________________________________________________");
		list.forEach(e -> System.out.println(e));
		LOGGER.info("End");
	}

	private static void testGetMovieListCustomer() {
		LOGGER.info("Start");
		List<Movie> list = movieService.getMovieListCustomer();
		System.out.println(String.format("%-3s %-20s %-15s %-8s %-30s %-18s %-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		list.forEach(e -> System.out.println(e));
		LOGGER.info("End");
	}

	private static void testModifyMovie() {
		LOGGER.info("Start");
		Movie movie = new Movie(1, "The Queen", "$2,514,512,988", true, DateUtil.convertToDate("16/08/2022"), "Fiction",
				false);
		movieService.modifyMovie(movie);
		LOGGER.info("End");
	}

	private static void testGetMovie(long id) {
		LOGGER.info("Start");
		Movie update = movieService.getMovie(id);
		System.out.println(String.format("%-3s %-20s %-15s %-8s %-30s %-18s" + " %-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		System.out.println(update);
		LOGGER.info("End");

	}

}
