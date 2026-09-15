package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address a1;
    private Address a2;

    @BeforeEach
    void setUp() {
        a1 = new Address(1L, "Beat", "Zimmermann", "Seestrasse 4", "0711112233", new Date());
        a2 = new Address(2L, "Alice", "Arnet", "Alpenquai 12", "0799998877", new Date());
    }

    @Test
    void testGetAllSorted() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Address> result = addressService.getAll();

        assertEquals(2, result.size());
        assertEquals("Arnet", result.get(0).getLastname());
        assertEquals("Zimmermann", result.get(1).getLastname());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(a1));
        assertTrue(addressService.getAddress(1L).isPresent());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(addressRepository.save(a1)).thenReturn(a1);
        Address saved = addressService.save(a1);
        assertEquals("Zimmermann", saved.getLastname());
        verify(addressRepository, times(1)).save(a1);
    }
}