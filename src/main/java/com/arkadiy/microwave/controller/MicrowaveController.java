package com.arkadiy.microwave.controller;

import com.arkadiy.microwave.entity.Mode;
import com.arkadiy.microwave.services.MicrowaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("microwave")
@CrossOrigin
public class MicrowaveController {
    @Autowired
    private MicrowaveService microwaveService;

    @PostMapping("")
    public ResponseEntity<Object> addMode(@RequestBody Mode modeNew) {
        Mode mode = microwaveService.saveMode(modeNew);

        return new ResponseEntity<>(mode, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateMode(@PathVariable("id") String id, @RequestBody Mode mode) {
        Mode modeUpdated = microwaveService.updateMode(Long.parseLong(id), mode);

        return new ResponseEntity<>(modeUpdated, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Mode>> getAllModes() {
        List<Mode> modes = microwaveService.getAllModes();

        return new ResponseEntity<>(modes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> startMode(@PathVariable("id") String id) {
        String response = microwaveService.startMode(Long.parseLong(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/repair")
    public ResponseEntity<String> repairMicrowave() {
        String response = microwaveService.repairMicrowave();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<String> microwaveInfo() {
        String response = microwaveService.getInfo();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
