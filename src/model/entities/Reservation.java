package model.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;

	DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation() {
	}
	
	public Reservation(Integer roomNumber, LocalDateTime checkIn, LocalDateTime checkOut) {
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}
	
	public long duration(LocalDateTime d1, LocalDateTime d2) {
		return Duration.between(d1, d2).abs().toDays();
	}
	
	public void updateDates(LocalDateTime checkIn, LocalDateTime checkOut) {
		LocalDateTime now = LocalDateTime.now();
		if(checkIn.isBefore(now) || checkOut.isBefore(now)) {
			throw new DomainException("Reservation dates for update must be future");
		}
		
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber 
				+ ", check-in: "
				+ checkIn.format(fmt1)
				+ ", checkOut: "
				+ checkOut.format(fmt1)
				+ ", "
				+ duration(checkOut, checkIn)
				+ " nights";
	}
	
}
