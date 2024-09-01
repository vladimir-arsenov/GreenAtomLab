package com.example.greenatomlab.dto;

import lombok.Data;

@Data
public class FileResponseDTO {
    private Long id;
    private String title;
    private String creationDate;
    private String description;
    private String fileData;
}