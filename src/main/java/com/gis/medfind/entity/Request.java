package com.gis.medfind.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.locationtech.jts.geom.Point;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;
    private String senderFullName;
    private String email;

    private String pharmacyName;

    private Point location;

    @Column(name = "created_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String createdDate;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_license_file", referencedColumnName = "id")
    private FileInfo licenseFile;

}
