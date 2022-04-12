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
import org.springframework.web.bind.annotation.RestController;
import mx.com.globant.hotel.entities.Room;
import mx.com.globant.hotel.service.RoomService;

@RestController
@RequestMapping(value="/api/room")

public class RoomController {
	
	Logger log= Logger.getLogger("LOG CRUD HBN ");
	FileHandler fileHandler;	
	
	@Autowired
	private RoomService roomService;	
	
	@PostMapping
	public ResponseEntity<Room> altaRoom(@RequestBody Room nuevoRoom){		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			roomService.create(nuevoRoom);			
			log.info("\nSe da de alta el cuarto " + nuevoRoom.getName());
			return new ResponseEntity<Room>(nuevoRoom,HttpStatus.CREATED);			
		}catch(Exception e){
			log.info("Error al dar de alta un cuarto: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Room>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}	
	
	@DeleteMapping("/{id}")
	public void borrarRoom(@PathVariable Long id){		
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			roomService.deleteById(id);
			log.info("\nSe da de baja el cuarto con id: " + id);	
		
		}catch(Exception e) {
			log.info("Error al dar de baja el cuarto: " + e.getMessage());
			e.printStackTrace();			
		}		
	}
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Room> consultaRoom(@PathVariable Long id){
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new  SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			Room cuartoConsulta = roomService.getById(id)
					                         .orElseThrow(
													() -> new NullPointerException(""));
			
			log.info("\nSe consulta el cuarto con id: " + id);
			return new ResponseEntity<Room>(cuartoConsulta,HttpStatus.OK); 
		}catch(NullPointerException e) {
			
			log.info("Valor null devuelto por la base al realizar la consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);			
		}catch(Exception e) {
			log.info("Error al consultar del cuarto con id: " + id + e.getMessage());
			e.printStackTrace();			
			return new ResponseEntity<Room>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}	
	
	@GetMapping
	public ResponseEntity<List<Room>> consultaGeneralCuartos(){
		try {
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
                    + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");

			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);		
			ArrayList<Room> listacuartos = (ArrayList<Room>) roomService.getAll();			
			log.info("\nSe realiza la consulta de los invitados");			 
			return new ResponseEntity<List<Room>>(listacuartos, HttpStatus.OK);
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Room>>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
	
	@PatchMapping	
	public ResponseEntity<Room> actualizarCuarto(@RequestBody Room roomAct) {
		try {
			
			fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
                    + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");

			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			
			Room room = roomService.getById(roomAct.getId())
			                .orElseThrow( 
			               		 ()-> new NullPointerException("El cuarto no existe"));
			
			room.setDescription(roomAct.getDescription());
			room.setFloor(roomAct.getFloor());
			room.setHotel(roomAct.getHotel());
			room.setMax_guests(roomAct.getMax_guests());
			room.setName(roomAct.getName());
			room.setType(roomAct.getType());
			
			roomService.update(roomAct);
			
			log.info("\nSe realiza la actualización del cuarto");			 
			return new ResponseEntity<Room>(roomAct, HttpStatus.OK);			
			
		}catch(NullPointerException e) {
			log.info("Error al actualizar cuarto\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);	
		}		
		catch(Exception e) {
			log.info("Error al actualizar cuarto\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Room>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}
}
