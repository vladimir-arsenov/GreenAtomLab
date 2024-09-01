package com.example.greenatomlab.dto;

import lombok.Data;

@Data
public class FileRequestDTO {
    private String title;
    private String creationDate; // В формате ISO 8601
    private String description;
    private String fileData; // Base64
}