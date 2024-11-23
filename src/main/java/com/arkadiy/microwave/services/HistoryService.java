package com.arkadiy.microwave.services;

import com.arkadiy.microwave.entity.History;
import com.arkadiy.microwave.entity.Mode;
import com.arkadiy.microwave.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History createRecord(Mode mode) {
        History history = new History();
        history.setName(mode.getName());
        history.setDuration(mode.getDuration());
        history.setPower(mode.getPower());
        return historyRepository.save(history);
    }

    public List<History> getAllHistory() {
        return historyRepository.findAll().reversed();
    }

    public List<Object[]> getTopModes() {
        List<Object[]> list = historyRepository.getTopModes();

        return list;
    }
}
