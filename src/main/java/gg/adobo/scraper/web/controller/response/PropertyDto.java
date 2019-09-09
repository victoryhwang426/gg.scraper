package gg.adobo.scraper.web.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PropertyDto {
    private String propertyName;
    private String location;
    private String lat;
    private String lng;
    private String bedRoomCount;
    private String bathRoomCount;
    private String propertySize;
    private String propertyDetail;
    private String postalCode;
    private List<String> amenities;

    @Builder
    public PropertyDto(String propertyName,
                       String propertySize,
                       String location,
                       String lat,
                       String lng,
                       String bedRoomCount,
                       String bathRoomCount,
                       String propertyDetail,
                       String postalCode,
                       List<String> amenities) {
        this.propertyName = propertyName;
        this.propertySize = propertySize;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.bedRoomCount = bedRoomCount;
        this.bathRoomCount = bathRoomCount;
        this.propertyDetail = propertyDetail;
        this.postalCode = postalCode;
        this.amenities = amenities;
    }
}
