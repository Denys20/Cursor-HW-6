package com.example.JpaHibernate;

import com.example.JpaHibernate.entity.Users;
import com.example.JpaHibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.setOut(new PrintStream(System.out, true, "UTF-8"));
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		int number;
		do {
			System.out.println("Меню:" +
					"\n1 Створити користувача" +
					"\n2 Видалити по id" +
					"\n3 Список всіх користувачів" +
					"\n4 Отримати користувача по id" +
					"\n5 Отримати користувача по email" +
					"\n6 Оновити інформацію про користувача" +
					"\n7 Вихід");
			number = scanner.nextInt();

			switch (number) {
				case 1:
					System.out.print("Введіть ім'я: ");
					String firstName = scanner.next();
					System.out.print("Введіть прізвище: ");
					String lastName = scanner.next();
					System.out.print("Введіть email: ");
					String email = scanner.next();
					System.out.print("Введіть вік: ");
					int age = scanner.nextInt();

					Users userAdd = new Users(firstName, lastName, email, age);
					userAdd = userRepository.save(userAdd);
					System.out.println("Додано користувача: " + userAdd);
					break;
				case 2:
					System.out.print("Введіть id для видалення: ");
					long id = scanner.nextLong();

					if(userRepository.delete(id)) {
						System.out.println("Користувача з id = " + id + " видалено");
					} else {
						System.out.println("Користувача з id = " + id + " не видалено");
					}
					break;
				case 3:
					List<Users> userAll = userRepository.getAll();
					if(userAll == null || userAll.size() == 0) {
						System.out.println("Список пустий");
					} else {
						System.out.println("Список користувачів:");
						for (Users user : userAll) {
							System.out.println(user);
						}
					}
					break;
				case 4:
					System.out.print("Введіть id користувача: ");
					long idUser = scanner.nextLong();
					Users user = userRepository.getById(idUser);
					if(user == null) {
						System.out.println("Користувач із id = " + idUser + " не знайдено");
					} else {
						System.out.println(user);
					}
					break;
				case 5:
					System.out.print("Введіть email користувача: ");
					String emailUser = scanner.next();
					List<Users> list = userRepository.getByEmail(emailUser);
					if(list == null || list.size() == 0) {
						System.out.println("Список пустий");
					} else {
						System.out.println("Список користувачів:");
						for (Users us : list) {
							System.out.println(us);
						}
					}
					break;
				case 6:
					System.out.print("Введіть id користувача для оновлення: ");
					long idUpdate = scanner.nextLong();
					Users userUpdate = userRepository.getById(idUpdate);
					if(userUpdate == null) {
						System.out.println("Такого користувача не знайдено");
					} else{
						System.out.print("Введіть ім'я: ");
						String firstNameUpdate = scanner.next();
						System.out.print("Введіть прізвище: ");
						String lastNameUpdate = scanner.next();
						System.out.print("Введіть email: ");
						String emailUpdate = scanner.next();
						System.out.print("Введіть вік: ");
						int ageUpdate = scanner.nextInt();

						userRepository.save(new Users(idUpdate, firstNameUpdate, lastNameUpdate, emailUpdate, ageUpdate));
					}
					break;
				case 7:
					System.out.println("Вихід");
					break;
				default:
					System.out.println("Невірний вибір. Cпробуйте ще раз.");
					break;
			}
		} while (number != 7);
	}
}
