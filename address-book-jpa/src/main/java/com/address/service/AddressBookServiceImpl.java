package com.address.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.exception.AddressNotFoundException;
import com.address.exception.MongoDBException;
import com.address.mappers.MapStructMapper;
import com.address.repository.AddressBookRepository;
import com.address.repository.AddressBookRepositoryCustom;
import com.address.repository.entity.AddressEntity;
import com.address.rest.dto.AddressDetailDto;
import com.address.rest.dto.AddressDto;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressBookServiceImpl.class);

	@Autowired
	private MapStructMapper mapstructMapper;

	@Autowired
	private AddressBookRepository addressBookRepository;

	@Autowired
	private AddressBookRepositoryCustom addressBookRepositoryCustom;
	
	@Override
	public AddressDto saveAddress(AddressDto addressDto) {
		var addressEntity = mapstructMapper.addressDtoToAddressEntity(addressDto);
		try {
			return mapstructMapper.addressEntityToAddressDto(addressBookRepository.save(addressEntity));
		} catch (Exception ex) {
			throw new MongoDBException(ex);
		}
	}

	@Override
	public AddressDetailDto searchAddress(String addressField) {

		List<AddressEntity> addressEntities = Optional.ofNullable(addressBookRepositoryCustom.findAllByAny(addressField))
				.filter(address -> !address.isEmpty()).orElseThrow(() -> {
					LOGGER.error("Address not found in database for search field : {} ", addressField);
					throw new AddressNotFoundException("Address not found for :: " + addressField);
				});

		List<AddressDto> addressDtos = mapstructMapper.addressEntityToAddressDto(addressEntities);
		return new AddressDetailDto(addressDtos);
	}

}
