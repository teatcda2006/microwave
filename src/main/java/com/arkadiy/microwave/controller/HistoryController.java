package com.arkadiy.microwave.controller;

import com.arkadiy.microwave.entity.History;
import com.arkadiy.microwave.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("history")
@CrossOrigin
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("")
    public ResponseEntity<List<History>> getAllHistory() {
        List<History> history = historyService.getAllHistory();

        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<List<Object[]>> getTopModes() {
        List<Object[]> topOfHistory = historyService.getTopModes();

        return new ResponseEntity<>(topOfHistory, HttpStatus.OK);
    }
}
