package gg.adobo.scraper.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gg.adobo.scraper.domain.entity.Amenity;
import gg.adobo.scraper.domain.entity.Property;
import gg.adobo.scraper.domain.repository.PropertyRepository;
import gg.adobo.scraper.util.HtmlParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertyParserService {
    @Autowired
    private HtmlParser htmlParser;

    @Autowired
    private PropertyRepository propertyRepository;

    private String BASE_URL = "https://www.abodo.com";

    private List<String> TARGET_POSTAL_CODES = Arrays.asList("60603", "60607");

    private Map<String, List<Property>> propertyMap;

    public boolean parseProperties(){
        String TARGET_URL = BASE_URL + "/chicago-il?page=";

        propertyMap = new HashMap<>();
        TARGET_POSTAL_CODES.forEach(
            postalCode -> propertyMap.put(postalCode, new ArrayList<>())
        );

        int maxPage = 100;

        loop :
        for(int curPage = 1; curPage <= maxPage; curPage ++){
            Document document = htmlParser.getDocument(TARGET_URL + curPage);

            List<String> propertyUrls = getPropertyUrls(document);
            for(String propertyUrl : propertyUrls){
                Optional<Property> optProperty = buildProperty(propertyUrl);
                if(optProperty.isPresent()){
                    Property property = optProperty.get();
                    if(propertyMap.get( property.getPostalCode() ).size() < 200){
                        propertyMap.get(property.getPostalCode()).add(property);
                        propertyRepository.save(property);
                    }

                    if(isExceedThreshold()){
                        break loop;
                    }
                }
            }
        }

        return true;
    }

    private boolean isExceedThreshold(){
        int THRESHOLD = 200;
        if(propertyMap.get("60603").size() >= THRESHOLD && propertyMap.get("60607").size() >= THRESHOLD){
            return true;
        }

        return false;
    }

    private Optional<Property> buildProperty(String propertyUrl){
        String TARGET_URL = BASE_URL + propertyUrl;

        Document document = htmlParser.getDocument(TARGET_URL);
        try {
            String propertyDetail = document.select("script[type=\"application/ld+json\"]").first().html();

            JsonNode jsonNode = new ObjectMapper().readTree(propertyDetail);

            String postalCode = jsonNode.get("address").get("postalCode").asText();
            String streetAddress = jsonNode.get("address").get("streetAddress").asText();
            String[] geographies = document.select("div#walkscore-map-pane").attr("data-src")
                    .split("&")[0].split("center=")[1].split(",");
            String location = document.select("span.property-map-address").text().trim();
            String propertyName = document.select("div[class=property-headline-container]")
                    .select("h1")
                    .text().trim();
            String bedRoom = document.select("div[class=listing-summary-bedrooms listing-summary-section]")
                    .select("div[class=listing-summary-detail]")
                    .text().trim();
            String bathRoom = document.select("div[class=listing-summary-bathrooms listing-summary-section]")
                    .select("div[class=listing-summary-detail]")
                    .text().trim();
            String size = document.select("div[class=listing-summary-unit-size listing-summary-section]")
                    .select("div[class=listing-summary-detail]")
                    .text().trim();
            String description = document.select("div[class=content-row-info col-xs-12 col-sm-8 no-pad]")
                    .select("div[class=shortentext]")
                    .text().trim();

            if(TARGET_POSTAL_CODES.contains(postalCode)){
                Property p = Property.builder()
                        .propertyName(propertyName)
                        .propertyDetail(description)
                        .bedRoomCount(bedRoom)
                        .bathRoomCount(bathRoom)
                        .location(location)
                        .propertySize(size)
                        .lat(geographies[0])
                        .lng(geographies[1])
                        .postalCode(postalCode)
                        .build();
                p.addAbilities(buildAmenities(document).stream().map(name -> Amenity.builder().amenityName(name).build()).collect(Collectors.toSet()));
                return Optional.of(p);
            }

            return Optional.empty();

        } catch(Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private List<String> buildAmenities(Document document){
        return document.select("span.amenity-name").stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }

    private List<String> getPropertyUrls(Document document){
        Elements elements = document.select("div[class=search-grid-content]")
                .select("div[class=grid-text grid-property-name row grid-property-name-row]")
                .select("a");

        return elements.stream()
                .map(e -> e.attr("href"))
                .collect(Collectors.toList());
    }
}
