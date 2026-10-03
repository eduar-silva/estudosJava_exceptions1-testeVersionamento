package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) {
		
		//solução nível 1 (solução de tratamento de exceções muito ruim)
		
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.println("Check-in date (dd/MM/yyyy)");
		LocalDate checkIn = LocalDate.parse(sc.next(), fmt1);
		System.out.println("Check-out date (dd/MM/yyyy)");
		LocalDate checkOut = LocalDate.parse(sc.next(), fmt1);
		
		if (!checkOut.isAfter(checkIn)) {
			System.out.println("Error in reservation: check-out date must be after check-in date");
		}
		else {
			Reservation reservation = new Reservation(number, checkIn.atStartOfDay(), checkOut.atStartOfDay());
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter dataa to update the reservation: ");
			System.out.println("Check-in date (dd/MM/yyyy)");
			checkIn = LocalDate.parse(sc.next(), fmt1);
			System.out.println("Check-out date (dd/MM/yyyy)");
			checkOut = LocalDate.parse(sc.next(), fmt1);
			
			LocalDate now = LocalDate.now();
			if(checkIn.isBefore(checkOut) || checkOut.isBefore(now)) {
				System.out.println("Error in reservation: Reservation dates for update must be future");
			}
			else if (!checkOut.isAfter(checkIn)) {
				System.out.println("Error in reservation: check-out date must be after check-in date");
			}
			else {
				reservation.updateDates(checkIn.atStartOfDay(), checkOut.atStartOfDay());
				System.out.println("Reservation: " + reservation);
			}
		}
		
		sc.close();
	}

}
