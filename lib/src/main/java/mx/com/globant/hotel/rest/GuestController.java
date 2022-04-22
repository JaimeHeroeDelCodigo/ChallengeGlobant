package mx.com.globant.hotel.rest;

import java.util.ArrayList;
import java.util.List;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
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
import org.springframework.web.bind.annotation.RestController;
import mx.com.globant.hotel.entities.Guest;
import mx.com.globant.hotel.entities.Kid;
import mx.com.globant.hotel.service.GuestService;
import mx.com.globant.hotel.service.KidService;

@RestController
@RequestMapping(value="/api/guest")
public class GuestController {
	Logger log = LogManager.getLogger();
	//Logger log= Logger.getLogger("LOG CRUD HBN ");
	//FileHandler fileHandler;	
	
	@Autowired
	private GuestService guestService;
	@Autowired 
	private KidService kidService;

	@PostMapping
	public ResponseEntity<Guest> altaGuest(@RequestBody Guest nuevoGuest){		
		try {
			
			log.info("\nSe da de alta el invitado " + nuevoGuest.getFirst_name());
			guestService.create(nuevoGuest);			
			log.info("\nSe da de alta el invitado " + nuevoGuest.getFirst_name());
			return new ResponseEntity<Guest>(nuevoGuest,HttpStatus.CREATED);			
		}catch(Exception e){			
			e.printStackTrace();
			return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PostMapping("/kid")
	public ResponseEntity<Kid> agregarNinio(@RequestBody Kid kid) {
		try {			
			kidService.create(kid);
			log.info("\nSe da de alta el menor de edad para el guest con id " +
			                                                  kid.getId_guest());
			return new ResponseEntity<Kid>(kid,HttpStatus.CREATED);			
		}catch(Exception e) {
			log.info("Error al agregar menor de edad a un invitado: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Kid>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}		
	}
	
	@DeleteMapping("/{id}")
	public void borrarGuest(@PathVariable Long  id) {
		try {			
			guestService.deleteById(id);			
			log.info("\nSe da de baja el invitado con id " + id);			
			
		}catch(Exception e) {
			log.info("Error al dar de baja a un invitado: " + e.getMessage());
			e.printStackTrace();
		}		
	}	
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Guest> consultaGuest(@PathVariable Long  id) {
		try {		
			 Guest guest = guestService
					                   .getById(id)
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
