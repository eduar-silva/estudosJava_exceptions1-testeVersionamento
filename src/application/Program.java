package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		
		//solução nível 3 (solução de tratamento de exceções boa)
		
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.println("Check-in date (dd/MM/yyyy)");
			LocalDate checkIn = LocalDate.parse(sc.next(), fmt1);
			System.out.println("Check-out date (dd/MM/yyyy)");
			LocalDate checkOut = LocalDate.parse(sc.next(), fmt1);
	
			Reservation reservation = new Reservation(number, checkIn.atStartOfDay(), checkOut.atStartOfDay());
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter dataa to update the reservation: ");
			System.out.println("Check-in date (dd/MM/yyyy)");
			checkIn = LocalDate.parse(sc.next(), fmt1);
			System.out.println("Check-out date (dd/MM/yyyy)");
			checkOut = LocalDate.parse(sc.next(), fmt1);
			
			reservation.updateDates(checkIn.atStartOfDay(), checkOut.atStartOfDay());
			System.out.println("Reservation: " + reservation);
		}
		catch (DateTimeParseException e){
			System.out.println("Invalid Date Format");
		}
		catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected Error");
		}
		
		sc.close();
	}

}
