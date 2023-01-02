package com.bum.webstore.domain.repository;

import java.util.List;

import com.bum.webstore.domain.RoadAddress;
import com.bum.webstore.domain.repository.impl.AddrSearchKey;

public interface AddressRepository {
	List<RoadAddress> searchAddress(String searchString);
}
