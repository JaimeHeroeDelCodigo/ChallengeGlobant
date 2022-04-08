package mx.com.globant.hotel.rest;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.service.HotelService;

@RestController
@RequestMapping(value="/api/hoteles")
public class HotelController {
	
	Logger log= Logger.getLogger("LOG CRUD HBN ");
	FileHandler fileHandler;	
	
	
	@Autowired
	private HotelService hotelService;
	
	//@RequestMapping(value="hotel/alta", method = RequestMethod.POST, consumes = "application/json")
	@PostMapping
	public void altaHotel(@RequestBody Hotel nuevoHotel){		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			hotelService.create(nuevoHotel);			
			log.info("\nSe da de alta el hotel " + nuevoHotel.getName());			
			
		}catch(Exception e) {
			log.info("Error al dar de alta un hotel: " + e.getMessage());
			e.printStackTrace();			
		}	
	}	
	
	@RequestMapping(value= "hotel/borrar",method = RequestMethod.DELETE)
	public void borrarHotel(@PathParam(value = "id_hotel") Long  id) {
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			hotelService.deleteById(id);			
			log.info("\nSe da de baja el hotel con id " + id);			
			
		}catch(Exception e) {
			log.info("Error al dar de baja un hotel: " + e.getMessage());
			e.printStackTrace();			
		}		
	}
	
	@RequestMapping(value= "hotel/consultaId", method = RequestMethod.GET)
	public ResponseEntity<Hotel> consultaHotel(@PathParam(value = "id_hotel") Long  id) {
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			 Hotel hotel = hotelService.getById(id)
					                   .orElseThrow(
					                		   ()->new NullPointerException("No existe el hotel con el id indicado"));			 
			 log.info("\nSe realiza la consulta el hotel con id " + id);
			 return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);			 			
			
		}catch(NullPointerException e) {
			log.info("Valor null devuelto por la base al realizar la consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
		}		
		catch(Exception e) {
			log.info("Error al dar de realizar consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Hotel>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}			
		
	}	
	
	@RequestMapping(value= "hotel/consultaGeneral", method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> consultaGeneralHotel() {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			ArrayList<Hotel> listaHotel = (ArrayList<Hotel>) hotelService.getAll();			
			log.info("\nSe realiza la consulta de hoteles");			 
			return new ResponseEntity<List<Hotel>>(listaHotel, HttpStatus.OK);			 			
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Hotel>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
	}
	
	@RequestMapping(value= "hotel/actualizar", method = RequestMethod.PATCH)	
	public ResponseEntity<Hotel> actualizarHotel(@RequestBody Hotel hotelAct) {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			Hotel hotel= hotelService.getById(hotelAct.getHotel_id())
		                             .orElseThrow( 
		                            		 ()-> new NullPointerException("El hotel no existe"));			
			
			hotel.setName(hotelAct.getName());
			hotel.setDescription(hotelAct.getDescription());
			hotel.setStars(hotelAct.getStars());
			
			hotelService.update(hotel);			
			log.info("\nSe realiza la actualización del hotel");			 
			return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);	 			
			
			 
		}catch(NullPointerException e) {
			log.info("Error al actualizar hotel\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);			
		}		
		catch(Exception e) {
			log.info("Error al actualizar hotel\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Hotel>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
		
	}
	
}
