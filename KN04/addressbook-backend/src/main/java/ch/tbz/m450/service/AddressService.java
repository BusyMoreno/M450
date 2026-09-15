package ch.tbz.m450.service;

import ch.tbz.m450.comparator.AddressComparator;
import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Optional<Address> getAddress(Long id) {
        return addressRepository.findById(id);
    }

    public List<Address> getAll() {
        List<Address> addresses = new ArrayList<>(addressRepository.findAll());
        addresses.sort(new AddressComparator());
        return addresses;
    }
}