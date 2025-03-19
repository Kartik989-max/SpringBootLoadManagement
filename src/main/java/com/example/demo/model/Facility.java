package com.example.demo.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loadingPoint;
    private String unloadingPoint;
    private Timestamp loadingDate;
    private Timestamp unloadingDate;
}
