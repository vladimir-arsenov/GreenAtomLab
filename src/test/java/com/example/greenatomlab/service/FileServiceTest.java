package com.example.greenatomlab.service;

import com.example.greenatomlab.dto.FileRequestDTO;
import com.example.greenatomlab.model.FileEntity;
import com.example.greenatomlab.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FileServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileService fileService;

    FileServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFile() {
        FileRequestDTO fileRequestDTO = new FileRequestDTO();
        fileRequestDTO.setTitle("Test File");
        fileRequestDTO.setCreationDate(LocalDateTime.now().toString());
        fileRequestDTO.setDescription("Test Description");
        fileRequestDTO.setFileData("VGhpcyBpcyBhIHRlc3QgZmlsZQ=="); // Base64

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(1L);
        when(fileRepository.save(any(FileEntity.class))).thenReturn(fileEntity);

        Long fileId = fileService.saveFile(fileRequestDTO);
        assertEquals(1L, fileId);
    }
}
