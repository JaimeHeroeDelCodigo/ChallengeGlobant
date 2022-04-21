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
import mx.com.globant.hotel.entities.Reservation;
import mx.com.globant.hotel.exception.GuestNotExistingException;
import mx.com.globant.hotel.exception.RoomNotExistingException;
import mx.com.globant.hotel.service.ReservationService;

@RestController
@RequestMapping(value="/api/reservations")
public class ReservationController {	
	//Logger log= Logger.getLogger("LOG CRUD HBN ");
	//FileHandler fileHandler;	
	Logger log = LogManager.getLogger();
	
	@Autowired
	private ReservationService reservationService;	

	@PostMapping
	public ResponseEntity<Reservation> altaGuest(@RequestBody Reservation nuevoReservation){		
		try {
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/
			
			reservationService.create(nuevoReservation);			
			log.info("\nSe registra la reservación con id " + nuevoReservation.getReservation_id());
			return new ResponseEntity<Reservation>(nuevoReservation,HttpStatus.CREATED);			
		}catch(GuestNotExistingException e) {
			log.info("Algunos invitados de la reservación no existe: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
		}catch(RoomNotExistingException e) {
			log.info("Algunos cuartos de la reservación no existe: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			log.info("Error al registrar una reservación: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping("/{id}")
	public void borrarRoom(@PathVariable Long id){		
		try {
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/
			
			reservationService.deleteById(id);
			log.info("\nSe da de baja la reservación: " + id);	
		
		}catch(Exception e) {
			log.info("Error al dar de baja la reservación: " + e.getMessage());
			e.printStackTrace();			
		}		
	}	
	
	@RequestMapping(value= "/consultaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reservation> consultaReservation(@PathVariable Long id){
		try {
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
	                + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");
			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new  SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/
			
			Reservation reservationConsulta = reservationService.getById(id)
					                         .orElseThrow(
													() -> new NullPointerException(""));
			
			log.info("\nSe consulta la reservación con id: " + id);
			return new ResponseEntity<Reservation>(reservationConsulta,HttpStatus.OK); 
		}catch(NullPointerException e) {
			
			log.info("Valor null devuelto por la base al realizar la consulta por id " +id + "\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);			
		}catch(Exception e) {
			log.info("Error al consultar del cuarto con id: " + id + e.getMessage());
			e.printStackTrace();			
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}	
	
	@GetMapping
	public ResponseEntity<List<Reservation>> consultaGeneralReservaciones(){
		try {
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
                    + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");

			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/		
			ArrayList<Reservation> listareservacion = (ArrayList<Reservation>) reservationService.getAll();			
			log.info("\nSe realiza la consulta de las reservaciones");			 
			return new ResponseEntity<List<Reservation>>(listareservacion, HttpStatus.OK);
			
		}catch(Exception e) {
			log.info("Error al realizar la consulta general\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<List<Reservation>>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}	
	
	@PatchMapping	
	public ResponseEntity<Reservation> actualizarReservacion(@RequestBody Reservation reservationAct) {
		try {
			
			/*fileHandler = new FileHandler("C:/Users/jaime.desantiago/eclipse-workspace/"
                    + "mx.com.globant.hotel/lib/src/main/resources/LOG-HBN.txt");

			log.addHandler(fileHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);*/
			
			Reservation reservation = reservationService.getById(reservationAct.getReservation_id())
			                .orElseThrow( 
			               		 ()-> new NullPointerException("La reservación no existe"));			
			
			reservation.setCheck_in(reservationAct.getCheck_in());
			reservation.setCheck_out(reservationAct.getCheck_out());
			reservation.setEnd_date(reservationAct.getEnd_date());
			reservation.setGuests(reservationAct.getGuests());
			reservation.setReservation_id(reservationAct.getReservation_id());
			reservation.setRooms(reservationAct.getRooms());
			reservation.setStart_date(reservationAct.getStart_date());			
			reservationService.update(reservation);
			log.info("\nSe realiza la actualización la reservación");			 
			return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);			
			
		}catch(NullPointerException e) {
			log.info("Error al actualizar reservación\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);	
		}		
		catch(Exception e) {
			log.info("Error al actualizar reservación\n" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}		
	}	
}
