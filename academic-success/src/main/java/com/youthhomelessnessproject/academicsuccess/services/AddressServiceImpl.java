package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Address;
import com.youthhomelessnessproject.academicsuccess.repositories.AddressRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    // Runtime constructor-based injection of AdminRepository dependency
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findAddressById(id);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteAddressById(id);
    }
}
