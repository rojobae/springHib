package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      carService.add(new Car("model1", 1, userService.listUsers().get(0)));
      carService.add(new Car("model2", 2, userService.listUsers().get(1)));
      carService.add(new Car("model3", 3, userService.listUsers().get(2)));
      carService.add(new Car("model4", 4, userService.listUsers().get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listCars();

      for (Car car : cars) {
         System.out.println("Id = " + car.getUser().getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("model1", 1));
      System.out.println(userService.getUserByCar("model2", 2));
      System.out.println(userService.getUserByCar("model3", 3));
      System.out.println(userService.getUserByCar("model4", 4));

      context.close();
   }
}
