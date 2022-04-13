package mx.com.globant.hotel.exception;

public class RoomAlreadyExistsException extends Exception{	
	private static final long serialVersionUID = 3166217067646387038L;	
	public String message;	
	public RoomAlreadyExistsException() {}	
	public RoomAlreadyExistsException(String m) {
		this.message  = m;		
	}	
}
