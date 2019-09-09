package gg.adobo.scraper.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue
    private Long propertyId;
    @Column
    private String propertyName;
    @Column
    private String location;
    @Column
    private String lat;
    @Column
    private String lng;
    @Column
    private String bedRoomCount;
    @Column
    private String bathRoomCount;
    @Column
    private String propertySize;
    @Column(columnDefinition="LONGTEXT")
    private String propertyDetail;
    @Column
    private String postalCode;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private Set<Amenity> amenities = new LinkedHashSet<>();

    @Builder
    public Property(String propertyName,
                    String location,
                    String lat,
                    String lng,
                    String bedRoomCount,
                    String bathRoomCount,
                    String propertySize,
                    String propertyDetail,
                    String postalCode) {
        this.propertyName = propertyName;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.bedRoomCount = bedRoomCount;
        this.bathRoomCount = bathRoomCount;
        this.propertySize = propertySize;
        this.propertyDetail = propertyDetail;
        this.postalCode = postalCode;
    }

    public void addAbilities(Set<Amenity> amenities){
        for(Amenity amenity : amenities){
            addAbility(amenity);
        }
    }

    public void addAbility(Amenity amenity){
        amenities.add(amenity);
        amenity.updateProperty(this);
    }
}
