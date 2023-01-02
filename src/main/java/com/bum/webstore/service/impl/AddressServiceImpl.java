package com.bum.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bum.webstore.domain.RoadAddress;
import com.bum.webstore.domain.repository.AddressRepository;
import com.bum.webstore.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<RoadAddress> searchAddress(String searchString) {
		return addressRepository.searchAddress(searchString);
	}
}
