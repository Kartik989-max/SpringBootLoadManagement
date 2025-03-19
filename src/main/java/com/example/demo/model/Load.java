package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "loads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Load implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "load_id", updatable = false, nullable = false)
    private UUID loadId;

    @Version  // Ensures Hibernate tracks updates correctly
    private Integer version;

    @Embedded
    private Facility facility;

    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;
    private String shipperId;
    private Timestamp date;

    @PrePersist
    public void prePersist() {
        if (version == null) {
            version = 0;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.version += 1;
    }
}
