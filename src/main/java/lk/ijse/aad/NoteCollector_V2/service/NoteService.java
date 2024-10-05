package lk.ijse.aad.NoteCollector_V2.service;



import lk.ijse.aad.NoteCollector_V2.dto.NoteStatus;
import lk.ijse.aad.NoteCollector_V2.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
        void saveNote(NoteDTO noteDTO);
        List<NoteDTO> getAllNotes();
        NoteStatus getNote(String noteId);
        void deleteNote(String noteId);
        boolean updateNote(String noteId, NoteDTO noteDTO);

}
