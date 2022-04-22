package mx.com.globant.hotel.rest;

import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import mx.com.globant.hotel.entities.Hotel;
import mx.com.globant.hotel.service.HotelService;

@RestController
@RequestMapping(value="/api/hoteles")
public class HotelController {
	Logger log = LogManager.getLogger();
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping(consumes="application/json")
	@ResponseBody
	public ResponseEntity<Hotel> altaHotel(@RequestBody Hotel nuevoHotel){		
		try {	        	
			System.out.println("\n*****************************************\nEL hotel es\n");
			System.out.println(nuevoHotel);				
			
			hotelService.create(nuevoHotel);			
			log.info("\nSe da de alta el hotel " + nuevoHotel.getName());
			return new ResponseEntity<Hotel>(nuevoHotel,HttpStatus.CREATED);
			
		}catch(Exception e) {
			log.info("Error al dar de alta un hotel: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Hotel>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}	
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> borrarHotel(@PathVariable Long id) {
		try {			
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/		
			
			hotelService.deleteById(id);			
			log.info("\nSe da de baja el hotel con id " + id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
		}catch(Exception e) {
			log.info("Error al dar de baja un hotel: " + e.getMessage());			
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Hotel> consultaHotel(@PathVariable Long  id) {
		try {			
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
	
	@GetMapping
	public ResponseEntity<List<Hotel>> consultaGeneralHotel() {		
		try {
					
			LinkedList<Hotel> listaHotel = (LinkedList<Hotel>) hotelService.getAll();			
			log.info("\nSe realiza la consulta de hoteles");			 
			return new ResponseEntity<List<Hotel>>(listaHotel, HttpStatus.OK);			 			
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Hotel>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
	}
	
	@PatchMapping	
	public ResponseEntity<Hotel> actualizarHotel(@RequestBody Hotel hotelAct) {		
		try {			
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
