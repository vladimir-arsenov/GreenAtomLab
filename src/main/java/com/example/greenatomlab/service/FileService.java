package com.example.greenatomlab.service;


import com.example.greenatomlab.dto.FileRequestDTO;
import com.example.greenatomlab.dto.FileResponseDTO;
import com.example.greenatomlab.model.FileEntity;
import com.example.greenatomlab.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public Long saveFile(FileRequestDTO fileRequestDTO) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setTitle(fileRequestDTO.getTitle());
        fileEntity.setCreationDate(LocalDateTime.parse(fileRequestDTO.getCreationDate(), DateTimeFormatter.ISO_DATE_TIME));
        fileEntity.setDescription(fileRequestDTO.getDescription());
        fileEntity.setFileData(Base64.getDecoder().decode(fileRequestDTO.getFileData()));

        FileEntity savedFile = fileRepository.save(fileEntity);
        return savedFile.getId();
    }

    public FileResponseDTO getFile(Long id) {
        FileEntity fileEntity = fileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));

        FileResponseDTO fileResponseDTO = new FileResponseDTO();
        fileResponseDTO.setId(fileEntity.getId());
        fileResponseDTO.setTitle(fileEntity.getTitle());
        fileResponseDTO.setCreationDate(fileEntity.getCreationDate().toString());
        fileResponseDTO.setDescription(fileEntity.getDescription());
        fileResponseDTO.setFileData(Base64.getEncoder().encodeToString(fileEntity.getFileData()));

        return fileResponseDTO;
    }

    public List<FileResponseDTO> getAllFiles(int page, int size) {
        return fileRepository.findAll(PageRequest.of(page, size, Sort.by("creationDate").descending()))
            .stream()
            .map(this::convertToFileResponseDTO)
            .collect(Collectors.toList());
    }

    private FileResponseDTO convertToFileResponseDTO(FileEntity fileEntity) {
        FileResponseDTO fileResponseDTO = new FileResponseDTO();
        fileResponseDTO.setId(fileEntity.getId());
        fileResponseDTO.setTitle(fileEntity.getTitle());
        fileResponseDTO.setCreationDate(fileEntity.getCreationDate().toString());
        fileResponseDTO.setDescription(fileEntity.getDescription());
        fileResponseDTO.setFileData(Base64.getEncoder().encodeToString(fileEntity.getFileData()));
        return fileResponseDTO;
    }
}
