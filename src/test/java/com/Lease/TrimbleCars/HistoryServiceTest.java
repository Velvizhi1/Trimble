package com.Lease.TrimbleCars;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Lease.TrimbleCars.model.History;
import com.Lease.TrimbleCars.repository.HistoryRepo;
import com.Lease.TrimbleCars.service.HistoryService;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @Mock
    private HistoryRepo historyRepo;

    @InjectMocks
    private HistoryService historyService;

    private History testHistory;

    @BeforeEach
    void setUp() {
        testHistory = new History(1L, 1L, "John Doe", "Customer", null, null, null);
    }

    @Test
    void testLeaseHistory_Success() {
        when(historyRepo.findByLeaserId(1L)).thenReturn(List.of(testHistory));
        
        List<History> historyList = historyService.LeaseHistory(1L);
        
        assertNotNull(historyList);
        assertEquals(1, historyList.size());
        verify(historyRepo, times(1)).findByLeaserId(1L);
    }
}
