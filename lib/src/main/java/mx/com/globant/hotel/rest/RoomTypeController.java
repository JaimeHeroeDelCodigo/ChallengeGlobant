package mx.com.globant.hotel.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
import mx.com.globant.hotel.entities.RoomType;
import mx.com.globant.hotel.service.RoomTypeService;

@RestController
@RequestMapping(value="api/roomtype")
public class RoomTypeController {	
	Logger log= Logger.getLogger("LOG CRUD HBN ");
	FileHandler fileHandler;	
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	@PostMapping(consumes="application/json")
	@ResponseBody
	public ResponseEntity<RoomType> altaTipoDeCuarto(@RequestBody RoomType nuevoRoomType){		
		try {	        	
			System.out.println("\n*****************************************\nEL hotel es\n");
			System.out.println(nuevoRoomType);
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			roomTypeService.create(nuevoRoomType);			
			log.info("\nSe da de alta el tipo de cuarto " + nuevoRoomType.getName());
			return new ResponseEntity<RoomType>(nuevoRoomType,HttpStatus.CREATED);
			
		}catch(Exception e) {
			log.info("Error al dar de alta un tipo de cuarto: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RoomType>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}	
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus>  borrarTipoDeCuarto(@PathVariable Long id) {
		try {			
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			roomTypeService.deleteById(id);			
			log.info("\nSe da de baja el tipo de cuarto con id:" + id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
		}catch(Exception e) {
			log.info("Error al dar de baja un tipo de cuarto: " + e.getMessage());			
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoomType> consultaTipoDeCuarto(@PathVariable Long  id) {
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			
			RoomType roomType = roomTypeService.getById(id)
					                           .orElseThrow(
					                		        ()->new NullPointerException("No existe el tipo de cuarto con el id indicado"));			 
			log.info("\nSe realiza la consulta del tipo de cuarto con id " + id);
			return new ResponseEntity<RoomType>(roomType, HttpStatus.OK);			 			
			
		}catch(NullPointerException e) {
			log.info("Valor null devuelto por la base al realizar la consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RoomType>(HttpStatus.NOT_FOUND);
		}		
		catch(Exception e) {
			log.info("Error al dar de realizar consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RoomType>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}	
	
	@GetMapping
	public ResponseEntity<List<RoomType>> consultaGeneralTipoDeCuarto() {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			ArrayList<RoomType> listaTiposDeCuarto = (ArrayList<RoomType>) roomTypeService.getAll();			
			log.info("\nSe realiza la consulta de tipos de cuarto");			 
			return new ResponseEntity<List<RoomType>>(listaTiposDeCuarto, HttpStatus.OK);			 			
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<RoomType>>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
	}
	
	@PatchMapping	
	public ResponseEntity<RoomType> actualizarHotel(@RequestBody RoomType roomTypeAct) {		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
					                     + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			RoomType roomType = roomTypeService.getById(roomTypeAct.getId())
		                                       .orElseThrow( 
		                            		         ()-> new NullPointerException("El tipo de cuarto no existe"));			
			
			roomType.setName(roomTypeAct.getName());			
			roomTypeService.update(roomType);			
			log.info("\nSe realiza la actualización del tipo de cuarto");			 
			return new ResponseEntity<RoomType>(roomType, HttpStatus.OK);		
			 
		}catch(NullPointerException e) {
			log.info("Error al actualizar el tipo de cuarto\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RoomType>(HttpStatus.NOT_FOUND);			
		}		
		catch(Exception e) {
			log.info("Error al actualizar el tipo de cuarto\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<RoomType>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
	}
}
