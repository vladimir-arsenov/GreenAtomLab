package com.example.greenatomlab.controller;

import com.example.greenatomlab.dto.FileRequestDTO;
import com.example.greenatomlab.dto.FileResponseDTO;
import com.example.greenatomlab.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Long> createFile(@RequestBody FileRequestDTO fileRequestDTO) {
        Long fileId = fileService.saveFile(fileRequestDTO);
        return ResponseEntity.ok(fileId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponseDTO> getFile(@PathVariable Long id) {
        FileResponseDTO fileResponseDTO = fileService.getFile(id);
        return ResponseEntity.ok(fileResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<FileResponseDTO>> getAllFiles(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        List<FileResponseDTO> files = fileService.getAllFiles(page, size);
        return ResponseEntity.ok(files);
    }
}
