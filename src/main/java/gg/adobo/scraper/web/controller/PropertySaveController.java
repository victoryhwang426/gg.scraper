package gg.adobo.scraper.web.controller;

import gg.adobo.scraper.domain.service.PropertyParserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("properties/parse")
public class PropertySaveController {
    private PropertyParserService propertyParserService;

    @PostMapping
    public boolean parseProperties() {
        return propertyParserService.parseProperties();
    }
}
