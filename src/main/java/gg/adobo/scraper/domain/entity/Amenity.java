package gg.adobo.scraper.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "amenity")
public class Amenity {
    @Id
    @GeneratedValue
    private Long amenityId;

    @Column
    private String amenityName;

    @ManyToOne
    @JoinColumn(name = "propertyId")
    private Property property;

    @Builder
    public Amenity(String amenityName, Property property) {
        this.amenityName = amenityName;
        this.property = property;
    }

    public void updateProperty(Property property) {
        this.property = property;
    }
}
