package gg.adobo.scraper.domain.repository;

import gg.adobo.scraper.domain.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByBedRoomCount(String bedRoomCount);

    List<Property> findByBathRoomCount(String bathRoomCount);
}
