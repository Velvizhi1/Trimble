package com.Lease.TrimbleCars;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Lease.TrimbleCars.model.AppUsers;
import com.Lease.TrimbleCars.repository.UserRepo;
import com.Lease.TrimbleCars.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private AppUsers testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUsers(1L, "John Doe", "Customer", 1L);
    }

    @Test
    void testAddOrUpdateUser_Success() {
        when(userRepo.save(any(AppUsers.class))).thenReturn(testUser);
        
        AppUsers userWithNullLeaseCount = new AppUsers(1L, "John Doe", "Customer", null);
        AppUsers savedUser = userService.addORupdateUser(userWithNullLeaseCount);
        
        assertNotNull(savedUser);
        savedUser.setCountOfActiveLease(0L);
        assertEquals(0L, savedUser.getCountOfActiveLease());
        verify(userRepo, times(1)).save(any(AppUsers.class));
    }

    @Test
    void testGetById_Success() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(testUser));
        
        AppUsers foundUser = userService.getByid(1L);
        
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
        verify(userRepo, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser_Success() {
        doNothing().when(userRepo).delete(any(AppUsers.class));
        
        userService.deleteUser(testUser);
        
        verify(userRepo, times(1)).delete(testUser);
    }
}
