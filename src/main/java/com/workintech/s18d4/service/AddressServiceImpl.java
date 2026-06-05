package com.workintech.s18d4.service;

import com.workintech.s18d4.controller.AddressController;
import com.workintech.s18d4.dao.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        throw new RuntimeException("Address doesn't exist");
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Address oldAddress = findById(id);
        oldAddress.setNo(address.getNo());
        oldAddress.setCity(address.getCity());
        oldAddress.setCountry(address.getCountry());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setDescription(address.getDescription());
        oldAddress.setCustomer(address.getCustomer());
        return addressRepository.save(oldAddress);
    }

    @Override
    public Address delete(Long id) {
        Address address = findById(id);
        addressRepository.delete(address);
        return address;
    }
}
