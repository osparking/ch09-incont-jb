package ch09.airport;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch09.airport.producers.FlightProducer;

@RunWith(Arquillian.class)
public class FlightWithPassengersTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(Passenger.class, Flight.class, FlightProducer.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Flight flight;

	@Test(expected = RuntimeException.class)
	public void testNumberOfSeatsCannotBeExceeded() {
		assertEquals(20, flight.getNumberOfPassengers());
		flight.addPassenger(new Passenger("1247890", "민 준석"));
	}

	@Test
	public void testAddRemovePassengers() throws IOException {
		flight.setSeats(21);
		Passenger additionalPassenger = new Passenger("1247890", "민 준석");
		flight.addPassenger(additionalPassenger);
		assertEquals(21, flight.getNumberOfPassengers());
		flight.removePassenger(additionalPassenger);
		assertEquals(20, flight.getNumberOfPassengers());
		assertEquals(21, flight.getSeats());
	}
}
