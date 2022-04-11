package mx.com.globant.hotel.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.service.GuestService;

@RestController
@RequestMapping(value="/api/guest")
public class GuestController {
	
	Logger log= Logger.getLogger("LOG CRUD HBN ");
	FileHandler fileHandler;	
	
	@Autowired
	private GuestService guestService;
	

	@PostMapping
	public ResponseEntity<Guest> altaGuest(@RequestBody Guest nuevoGuest){		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			guestService.create(nuevoGuest);			
			log.info("\nSe da de alta el invitado " + nuevoGuest.getFirst_name());
			return new ResponseEntity<Guest>(nuevoGuest,HttpStatus.CREATED);
			
		}catch(Exception e){
			log.info("Error al dar de alta un invitado: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping
	public void borrarGuest(@PathParam(value = "id_guest") Long  id) {
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
                    + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");

			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			guestService.deleteById(id);			
			log.info("\nSe da de baja el invitado con id " + id);			
			
		}catch(Exception e) {
			log.info("Error al dar de baja a un invitado: " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	
	@RequestMapping(value= "/consultaId", method = RequestMethod.GET)
	public ResponseEntity<Guest> consultaGuest(@PathParam(value = "id_guest") Long  id) {
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			 Guest guest = guestService.getById(id)
					                   .orElseThrow(
					                		   ()->new NullPointerException("No existe el invitado con el id indicado"));			 
			 log.info("\nSe realiza la consulta el invitado con id " + id);
			 return new ResponseEntity<Guest>(guest, HttpStatus.OK);			 			
			
		}catch(NullPointerException e) {
			log.info("Valor null devuelto por la base al realizar la consulta por id " + id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}		
		catch(Exception e) {
			log.info("Error al dar de realizar consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}			
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<Guest>> consultaGeneralInvitados() {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			ArrayList<Guest> listaInvitados = (ArrayList<Guest>) guestService.getAll();			
			log.info("\nSe realiza la consulta de los invitados");			 
			return new ResponseEntity<List<Guest>>(listaInvitados, HttpStatus.OK);			 			
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Guest>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
	}
	
	@PatchMapping	
	public ResponseEntity<Guest> actualizarHotel(@RequestBody Guest guestAct) {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			Guest guest = guestService.getById(guestAct.getId())
		                             .orElseThrow( 
		                            		 ()-> new NullPointerException("El invitado no existe"));			
			
			guest.setFirst_name(guestAct.getFirst_name());			
			guest.setLast_name(guestAct.getLast_name());
			guest.setEmail(guestAct.getEmail());			
			guestService.update(guest);			
			log.info("\nSe realiza la actualización del invitado");			 
			return new ResponseEntity<Guest>(guest, HttpStatus.OK);			 
		}catch(NullPointerException e) {
			log.info("Error al actualizar invitado\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);			
		}		
		catch(Exception e) {
			log.info("Error al actualizar invitado\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}
	
	
	
	
	

}
