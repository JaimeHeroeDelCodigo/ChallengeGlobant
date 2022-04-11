package mx.com.globant.hotel;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import mx.com.globant.hotel.entities.Hotel;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HotelesBuenasNoches");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();

	    Hotel hotel = new Hotel();
	    
	    //hotel.setDescription("Hotel prueba");
	    //hotel.setName("TRANSILVANYA");
	    //hotel.setStars( (short) 5);

	   
	    
	    entityManager.getTransaction().begin();
	    entityManager.persist(hotel);
	    entityManager.getTransaction().commit();
	}
	
	
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(Application.class);
	  }
	
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			//System.out.println("Let's inspect the beans provided by Spring Boot:");
            System.out.println("*************************************************************************************************************");
            System.out.println("*************************************************************************************************************");
            System.out.println("*************************************************************************************************************");
            System.out.println("\n\n                               HOTEL BUENAS NOCHES\n");
            System.out.println("*************************************************************************************************************");
            System.out.println("*************************************************************************************************************");
            System.out.println("*************************************************************************************************************\n\n");
           
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
}
