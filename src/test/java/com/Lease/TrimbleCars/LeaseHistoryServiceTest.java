package com.Lease.TrimbleCars;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.repository.HistoryRepo;
import com.Lease.TrimbleCars.service.LeaseHistoryService;

@ExtendWith(MockitoExtension.class)
class LeaseHistoryServiceTest {

    @Mock
    private HistoryRepo historyRepo;

    @InjectMocks
    private LeaseHistoryService leaseHistoryService;

    private History testHistory;

    @BeforeEach
    void setUp() {
        testHistory = new History(1L, 1L, "John Doe", "Customer", null, null, null);
    }

    @Test
    void testCreateTransaction_Success() {
        when(historyRepo.save(any(History.class))).thenReturn(testHistory);
        
        String result = leaseHistoryService.createTransaction(testHistory);
        
        assertNotNull(result);
        assertEquals("the car has been booked sucessfully ", result);
        verify(historyRepo, times(1)).save(any(History.class));
    }
}
