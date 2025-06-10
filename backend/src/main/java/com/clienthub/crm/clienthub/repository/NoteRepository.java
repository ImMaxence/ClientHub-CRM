package com.clienthub.crm.clienthub.repository;

import com.clienthub.crm.clienthub.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
