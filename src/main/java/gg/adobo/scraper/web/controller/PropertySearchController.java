package gg.adobo.scraper.web.controller;

import gg.adobo.scraper.domain.service.PropertyService;
import gg.adobo.scraper.web.controller.response.PropertyDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("properties/search")
public class PropertySearchController {
    private PropertyService propertyService;

    @GetMapping("/bedroom/{bedRoom}")
    public List<PropertyDto> getPropertiesByBedRoom(@PathVariable String bedRoom){
        return propertyService.getPropertiesByBedRoom(bedRoom);
    }

    @GetMapping("/bathroom/{bathRoom}")
    public List<PropertyDto> getPropertiesByBathRoom(@PathVariable String bathRoom){
        return propertyService.getPropertiesByBathRoom(bathRoom);
    }
}
