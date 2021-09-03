package com.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.address.repository.entity.AddressEntity;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressEntity,Long> {

}