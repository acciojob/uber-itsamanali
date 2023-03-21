package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer customer = customerRepository2.findById(customerId).get();
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		Customer customer = customerRepository2.findById(customerId).get();

		TripBooking trip = new TripBooking();
		trip.setFromLocation(fromLocation);
		trip.setToLocation(toLocation);
		trip.setDistanceInKilometer(distanceInKm);

		List<Driver> drivers = driverRepository2.findAll();
		Driver availableDriver= new Driver();
		boolean flag = true;
		for (Driver d: drivers){
			if(d.getCab().getisAvailable()){
				d.getCab().setAvailable(false);
				availableDriver =d;
				flag =false;
			}
		}
		if(flag){
			throw new Exception("No cab available!");
		}
		trip.setBill(distanceInKm * availableDriver.getCab().getPerKmRate());
		trip.setTripstatus(TripStatus.CONFIRMED);
		trip.setCustomer(customer);
		trip.setDriver(availableDriver);

		List<TripBooking> tripBookingList=customer.getTripBookingList();
		tripBookingList.add(trip);

		List<TripBooking> tripBookingList1 = availableDriver.getTripBookingList();
		tripBookingList1.add(trip);

		customerRepository2.save(customer);
		driverRepository2.save(availableDriver);
		tripBookingRepository2.save(trip);

		return trip;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.findById(tripId).get();
		tripBooking.setTripstatus(TripStatus.COMPLETED);
		tripBooking.getDriver().getCab().setAvailable(true);


		tripBookingRepository2.save(tripBooking);


	}
}
