package com.youthhomelessnessproject.academicsuccess.repositories;

import com.youthhomelessnessproject.academicsuccess.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressById(Long id);

    void deleteAddressById(Long id);

}
