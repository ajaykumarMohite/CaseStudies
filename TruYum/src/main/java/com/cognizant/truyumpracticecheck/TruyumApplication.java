package com.cognizant.truyumpracticecheck;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cognizant.model.MenuItem;
import com.cognizant.service.MenuItemService;
import com.cognizant.util.DateUtil;


//SpringBootApplication Annotation comprised of 3 annotations
@SpringBootApplication
@EntityScan(basePackages = { "com" })
@ComponentScan(basePackages = { "com.cognizant.service" })
@EnableJpaRepositories(basePackages = { "com.cognizant.repository"})
public class TruyumApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TruyumApplication.class);
	private static MenuItemService menuItemService;
	
	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(TruyumApplication.class, args);
		menuItemService=context.getBean(MenuItemService.class);
		LOGGER.info("Inside main");
		Scanner sc = new Scanner(System.in);
		String choice;

		do {
			System.out.println("Menu");
			System.out.println("----------------------------------------");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.println("----------------------------------------");

			choice = sc.nextLine();
			System.out.println("----------------------------------------");

			switch (choice) {
			case "1": {
				String adminChoice;
				do {
					System.out.println("Admin Menu");
					System.out.println("----------------------------------------");
					System.out.println("1. Get Menu Item List");
					System.out.println("2. Modify Menu Item");
					System.out.println("3. Get Menu Item");
					System.out.println("4. Main Menu");
					System.out.println("----------------------------------------");

					adminChoice = sc.nextLine();
					System.out.println("----------------------------------------");

					switch (adminChoice) {
					case "1": {
						System.out.println("Admin Menu Item List");
						System.out.println("----------------------------------------");
						testGetMenuItemListAdmin(); 
						break;
					}
					case "2": {
						System.out.println("Item 2 is modified. Enter 3 to display the changes.");
						System.out.println("----------------------------------------");
						testModifyMenuItem(2);  // id no.2 is modifing in the impl
						break;
					}
					case "3": {
						System.out.println("2nd Menu Item is displayed");
						System.out.println("----------------------------------------");
						testGetMenuItem(2); //Modified item 2 is diplasyed
						break;
					}
					case "4": {
						break;
					}
					default: {
						System.out.println("Enter valid choice");
					}
					}
				} while (adminChoice.equals("1")||adminChoice.equals("2")||adminChoice.equals("3"));
				choice="3";
				break;
			}
			case "2": {
				System.out.println("Customer Menu Item List");
				System.out.println("----------------------------------------");
				testGetMenuItemListCustomer();
				break;
			}
			case "3": {
				System.out.println("Thank YOU");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Enter valid choice");
			}
			}
		} while (choice.equals("3"));
		System.out.println("Thank you");
		sc.close();

		}
	
	private static void testGetMenuItemListAdmin()
	{
		List<MenuItem> list=menuItemService.getMenuItemListAdmin();
		list.forEach(e->System.out.println(e));
		
	}
	private static void testGetMenuItemListCustomer()
	{
		List<MenuItem> list=menuItemService.getMenuItemListCustomer();
		list.forEach(e->System.out.println(e));
		
	}
	private static void testModifyMenuItem(int id)
	{
		MenuItem menuItem=new MenuItem("Cheese Roll", 155f, true,DateUtil.convertToDate("23/12/2017"), "Main Course", false);
		menuItemService.modifyItem(id, menuItem);
	}
	private static void testGetMenuItem(int id)
	{
		MenuItem list=menuItemService.getMenuItem(id);
		System.out.println(list);
	}

}
