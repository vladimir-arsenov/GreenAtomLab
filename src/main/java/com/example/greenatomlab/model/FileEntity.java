package com.example.greenatomlab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "files")
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    private String description;

    @Lob
    private byte[] fileData;
}

