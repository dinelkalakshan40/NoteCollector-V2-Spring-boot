package lk.ijse.aad.NoteCollector_V2.service;


import lk.ijse.aad.NoteCollector_V2.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.aad.NoteCollector_V2.dao.NoteDao;
import lk.ijse.aad.NoteCollector_V2.dto.NoteStatus;
import lk.ijse.aad.NoteCollector_V2.dto.impl.NoteDTO;
import lk.ijse.aad.NoteCollector_V2.entity.impl.NoteEntity;
import lk.ijse.aad.NoteCollector_V2.exception.DataPersistException;
import lk.ijse.aad.NoteCollector_V2.exception.NoteNotFoundException;
import lk.ijse.aad.NoteCollector_V2.util.AppUtil;
import lk.ijse.aad.NoteCollector_V2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping noteMapping;
    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        NoteEntity savedNote =
                noteDao.save(noteMapping.toNoteEntity(noteDTO));
        if(savedNote == null){
            throw new DataPersistException("Note not saved");
        }
    }


    @Override
    public List<NoteDTO> getAllNotes() {
        return noteMapping.asNoteDTOList( noteDao.findAll());
    }

    @Override
    public NoteStatus getNote(String noteId) {
        if(noteDao.existsById(noteId)){
            var selectedUser = noteDao.getReferenceById(noteId);
            return noteMapping.toNoteDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2,"Selected note not found");
        }
    }

    @Override
    public void deleteNote(String noteId) {
        Optional<NoteEntity> foundNote = noteDao.findById(noteId);
        if (!foundNote.isPresent()) {
            throw new NoteNotFoundException("Note not found");
        }else {
            noteDao.deleteById(noteId);
        }
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO noteDTO) {
        return false;
    }
}
