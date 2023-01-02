package com.bum.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bum.webstore.domain.RoadAddress;
import com.bum.webstore.domain.repository.AddressRepository;

@Service
public interface AddressService {

	public List<RoadAddress> searchAddress(String searchString);
}
