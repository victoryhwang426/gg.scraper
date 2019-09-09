package gg.adobo.scraper.domain.service;

import gg.adobo.scraper.domain.entity.Amenity;
import gg.adobo.scraper.domain.entity.Property;
import gg.adobo.scraper.domain.repository.PropertyRepository;
import gg.adobo.scraper.web.controller.response.PropertyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyDto> getPropertiesByBedRoom(String bedRoomCount){
        return propertyRepository.findByBedRoomCount(bedRoomCount).stream()
                .map(p -> PropertyDto.builder()
                        .bathRoomCount(p.getBathRoomCount())
                        .bedRoomCount(p.getBedRoomCount())
                        .lat(p.getLat())
                        .lng(p.getLng())
                        .location(p.getLocation())
                        .propertyDetail(p.getPropertyDetail())
                        .propertyName(p.getPropertyName())
                        .propertySize(p.getPropertySize())
                        .amenities(p.getAmenities().stream().map(Amenity::getAmenityName).collect(Collectors.toList()))
                        .postalCode(p.getPostalCode())
                        .build()
                ).collect(Collectors.toList());
    }

    public List<PropertyDto> getPropertiesByBathRoom(String bathRoomCount){
        return propertyRepository.findByBathRoomCount(bathRoomCount).stream()
                .map(p -> PropertyDto.builder()
                        .bathRoomCount(p.getBathRoomCount())
                        .bedRoomCount(p.getBedRoomCount())
                        .lat(p.getLat())
                        .lng(p.getLng())
                        .location(p.getLocation())
                        .propertyDetail(p.getPropertyDetail())
                        .propertyName(p.getPropertyName())
                        .propertySize(p.getPropertySize())
                        .amenities(p.getAmenities().stream().map(Amenity::getAmenityName).collect(Collectors.toList()))
                        .postalCode(p.getPostalCode())
                        .build()
                ).collect(Collectors.toList());
    }
}
