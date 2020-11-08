package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      carService.add(new Car("Ferrari", 1));
      carService.add(new Car("Ferrari", 2));
      carService.add(new Car("Ford", 1));
      carService.add(new Car("Ford", 2));

      userService.add(new User("User5", "Lastname5", "user5@mail.ru",
              new Car("Ferrari", 3)));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru",
              new Car("Ford", 3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      User userPr1 = userService.getUserCar("Ferrari", 3);
      User userPr2 = userService.getUserCar("Ford", 3);
      System.out.println(userPr1.getFirstName() + "-" + userPr1.getLastName() );
      System.out.println(userPr2.getFirstName() + "-" + userPr2.getLastName() );

      context.close();
   }
}
