package ch09.airport;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Flight {
	@NonNull
	private String flightNumber;
	@NonNull
	private int seats;
	Set<Passenger> passengers = new HashSet<>();

	public int getNumberOfPassengers() {
		return passengers.size();
	}

	public void setSeats(int seats) {
		if (passengers.size() > seats) {
			throw new RuntimeException(
					"Cannot reduce seats under the number of existing passengers!");
		}
		this.seats = seats;
	}

	public boolean addPassenger(Passenger passenger) {
		if (passengers.size() >= seats) {
			throw new RuntimeException(
					"Cannot add more passengers than the capacity of the flight!");
		}
		return passengers.add(passenger);
	}

	public boolean removePassenger(Passenger passenger) {
		return passengers.remove(passenger);
	}

	@Override
	public String toString() {
		return "Flight: " + getFlightNumber();
	}
}
