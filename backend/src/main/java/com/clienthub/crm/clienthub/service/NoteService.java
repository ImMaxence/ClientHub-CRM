package com.clienthub.crm.clienthub.service;

import com.clienthub.crm.clienthub.model.Note;
import com.clienthub.crm.clienthub.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note non trouvée"));
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note noteDetails) {
        Note note = getNoteById(id);
        note.setContent(noteDetails.getContent());
        // Ne pas modifier author, company, deal, createdAt
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }
}
