package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Address;

public interface AddressService {

    // CREATE
    Address saveAddress(Address address);

    // READ
    Address getAddressById(Long id);

    // DELETE
    void deleteAddressById(Long id);
}
