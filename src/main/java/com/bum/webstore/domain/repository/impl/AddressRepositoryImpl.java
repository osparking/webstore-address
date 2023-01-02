package com.bum.webstore.domain.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bum.webstore.domain.RoadAddress;
import com.bum.webstore.domain.repository.AddressRepository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<RoadAddress> searchAddress(String searchString) {
		AddrSearchKey searchKey = new AddrSearchKey();

		searchKey.set도로_건물(searchString);
		var addressList = AddressRepoHelper.getAddressList(searchKey, jdbcTemplate);
		return addressList;
	}

}