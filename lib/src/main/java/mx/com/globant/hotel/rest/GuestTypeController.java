package mx.com.globant.hotel.rest;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import java.util.logging.FileHandler;
//import java.util.logging.Logger;
//import java.util.logging.SimpleFormatter;
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
import mx.com.globant.hotel.entities.GuestType;
import mx.com.globant.hotel.service.GuestTypeService;

@RestController
@RequestMapping(value="/api/guesttype")
public class GuestTypeController {	
	//Logger log= Logger.getLogger("LOG CRUD HBN ");
	//FileHandler fileHandler;
	Logger log = LogManager.getLogger();
	
	@Autowired
	private GuestTypeService guestTypeService;	

	@PostMapping
	public ResponseEntity<GuestType> altaGuestType(@RequestBody GuestType nuevoGuestType){		
		try {			
			guestTypeService.create(nuevoGuestType);			
			log.info("\nSe da de alta el tipo de invitado " + nuevoGuestType.getName());
			return new ResponseEntity<GuestType>(nuevoGuestType,HttpStatus.CREATED);			
		}catch(Exception e){
			log.info("Error al dar de alta un tipo de invitado: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<GuestType>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping("/{id}")
	public void borrarGuestType(@PathVariable Long id) {
		try {			
			guestTypeService.deleteById(id);
						
			log.info("\nSe da de baja el tipo de invitado con id " + id);			
			
		}catch(Exception e) {
			log.info("Error al dar de baja a un tipo de invitado: " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<GuestType> consultaGuestType(@PathVariable Long  id) {
		try {		
			 GuestType guestType = guestTypeService
					                   .getById(id)
					                   .orElseThrow(
					                		   ()->new NullPointerException("No existe el invitado "
					                		   		                     + "con el id indicado"));			 
			 log.info("\nSe realiza la consulta del tipo de invitado con id " + id);
			 return new ResponseEntity<GuestType>(guestType, HttpStatus.OK);			 			
			
		}catch(NullPointerException e) {
			log.info("Valor null devuelto por la base al realizar la consulta por id " + id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<GuestType>(HttpStatus.NOT_FOUND);
		}		
		catch(Exception e) {
			log.info("Error al dar de realizar consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<GuestType>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}	
	
	@GetMapping
	public ResponseEntity<List<GuestType>> consultaGeneralTipoInvitado() {		
		try {					
			ArrayList<GuestType> listaInvitados = (ArrayList<GuestType>) guestTypeService.getAll();			
			log.info("\nSe realiza la consulta de los tipos de invitado");			 
			return new ResponseEntity<List<GuestType>>(listaInvitados, HttpStatus.OK);			 			
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<GuestType>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}	
	
	@PatchMapping	
	public ResponseEntity<GuestType> actualizarHotel(@RequestBody GuestType guestTypeAct) {		
		try {		
			GuestType guestType = guestTypeService
					                 .getById(guestTypeAct.getId())
		                             .orElseThrow( 
		                            		 ()-> new NullPointerException("El tipo de invitado no existe"));			
			guestType.setName(guestTypeAct.getName());
			
			log.info("\nSe realiza la actualización del tipo de invitado");			 
			return new ResponseEntity<GuestType>(guestType, HttpStatus.OK);			 
		}catch(NullPointerException e) {
			log.info("Error al actualizar el tipo de invitado\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<GuestType>(HttpStatus.NOT_FOUND);			
		}		
		catch(Exception e) {
			log.info("Error al actualizar el tipo de invitado\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<GuestType>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}
}
