package com.Lease.TrimbleCars;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Lease.TrimbleCars.model.Cars;
import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.model.AppUsers;
import com.Lease.TrimbleCars.repository.CarRepo;
import com.Lease.TrimbleCars.service.CarService;
import com.Lease.TrimbleCars.service.LeaseHistoryService;
import com.Lease.TrimbleCars.service.UserService;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepo carRepo;
    
    @Mock
    private LeaseHistoryService leaseHistoryService;
    
    @Mock
    private UserService userService;

    @InjectMocks
    private CarService carService;

    private Cars testCar;
    private History testHistory;
    private AppUsers testUser;

    @BeforeEach
    void setUp() {
        testCar = new Cars(1L, 101L, "Toyota", "Ideal", new ArrayList<>());
        testUser = new AppUsers(1L, "John Doe", "Customer", 1L);
        testHistory = new History(1L, 1L, "John Doe", "Customer", null, null, testCar);
    }

    @Test
    void testAddingOrEnrollingCars() {
        when(carRepo.save(any(Cars.class))).thenReturn(testCar);
        
        Cars savedCar = carService.AddingOrEnrollingCars(new Cars());
        
        assertNotNull(savedCar);
        assertEquals("Ideal", savedCar.getCarStatus());
        verify(carRepo, times(1)).save(any(Cars.class));
    }

    @Test
    void testCarStatus() {
        when(carRepo.getBycarOwnerId(101L)).thenReturn(List.of(testCar));
        
        List<Cars> cars = carService.carStatus(101L);
        
        assertFalse(cars.isEmpty());
        assertEquals(1, cars.size());
        verify(carRepo, times(1)).getBycarOwnerId(101L);
    }

    @Test
    void testLeaseHistory() {
        when(carRepo.findById(1L)).thenReturn(Optional.of(testCar));
        
        List<History> historyList = carService.LeaseHistory(1L);
        
        assertNotNull(historyList);
        assertEquals(testCar.getHistories(), historyList);
        verify(carRepo, times(1)).findById(1L);
    }

    @Test
    void testBookByID_Success() {
        when(userService.getByid(1L)).thenReturn(testUser);
        when(carRepo.findById(1L)).thenReturn(Optional.of(testCar));
        when(leaseHistoryService.createTransaction(any(History.class))).thenReturn("Transaction Success");
        when(carRepo.save(any(Cars.class))).thenReturn(testCar);
        when(userService.addORupdateUser(any(AppUsers.class))).thenReturn(testUser);
        
        String result = carService.BookByID(1L, testHistory);
        
        assertTrue(result.contains("Transaction Success"));
        verify(carRepo, times(1)).save(any(Cars.class));
        verify(userService, times(1)).addORupdateUser(any(AppUsers.class));
    }

    @Test
    void testGetAvailableCarsForLease() {
        when(carRepo.findBycarStatus("Ideal")).thenReturn(List.of(testCar));
        
        List<CarService.CarDTO> availableCars = carService.getAvailableCarsForLease();
        
        assertFalse(availableCars.isEmpty());
        assertEquals(1, availableCars.size());
        assertEquals(testCar.getCarName(), availableCars.get(0).carName());
        verify(carRepo, times(1)).findBycarStatus("Ideal");
    }
}
