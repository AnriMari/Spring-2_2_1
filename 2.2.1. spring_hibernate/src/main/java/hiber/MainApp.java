package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User elis = new User("Elis", "White", "ewhite@gmail.io");
      User maks = new User("Maks", "Black", "mblack@gmail.io");
      User alex = new User("Alex", "Green", "agreen@gmail.io");
      User bob = new User("Bob", "Yellow", "byellow@gmail.io");

      Car audi = new Car("Audi", 5);
      Car bmw = new Car("BMW", 3);
      Car rover = new Car("Land Rover", 2);
      Car gmc = new Car("GMC", 123);

      userService.add(elis.setCar(audi).setUser(elis));
      userService.add(maks.setCar(bmw).setUser(maks));
      userService.add(alex.setCar(rover).setUser(alex));
      userService.add(bob.setCar(gmc).setUser(bob));

      for (User user : userService.listUsers()) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW", 3));

      context.close();
   }
}
