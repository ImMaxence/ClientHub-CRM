package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Note;
import com.clienthub.crm.clienthub.service.NoteService;
import com.clienthub.crm.clienthub.dto.NoteRequest;
import com.clienthub.crm.clienthub.dto.NoteResponse;
import com.clienthub.crm.clienthub.dto.mapper.NoteResponseMapper;
import com.clienthub.crm.clienthub.dto.mapper.NoteRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return noteService.getAllNotes().stream().map(NoteResponseMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(NoteResponseMapper.toDto(noteService.getNoteById(id)));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest noteRequest) {
        Note created = noteService.createNote(NoteRequestMapper.toEntity(noteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(NoteResponseMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        Note updated = noteService.updateNote(id, NoteRequestMapper.toEntity(noteRequest));
        return ResponseEntity.ok(NoteResponseMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
