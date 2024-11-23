package com.arkadiy.microwave.services;

import com.arkadiy.microwave.entity.Microwave;
import com.arkadiy.microwave.entity.Mode;
import com.arkadiy.microwave.repository.ModesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class MicrowaveService {
    private final Microwave microwave;
    private final ModesRepository modesRepository;
    private final HistoryService historyService;
    private final HolidayService holidayService;

    @Autowired
    public MicrowaveService(ModesRepository modesRepository, HistoryService historyService, HolidayService holidayService) {
        this.microwave = new Microwave();
        this.historyService = historyService;
        this.modesRepository = modesRepository;
        this.holidayService = holidayService;
    }

    public Mode saveMode(Mode mode) {
        return modesRepository.save(mode);
    }

    public List<Mode> getAllModes() {
        return modesRepository.findAll();
    }

    public Mode updateMode(Long id, Mode mode) {
        return modesRepository.findById(id).map(updatedMode -> {
            updatedMode.setName(mode.getName());
            updatedMode.setDuration(mode.getDuration());
            updatedMode.setPower(mode.getPower());
            return modesRepository.save(updatedMode);
        }).orElseThrow(() -> new RuntimeException("Режим не найден"));
    }

    public boolean isWorkingTime() {
        LocalTime now = LocalTime.now();
        boolean withinWorkingHours = now.isAfter(LocalTime.of(7, 0)) && now.isBefore(LocalTime.of(23, 0));
        boolean isHoliday = holidayService.isHoliday();

        return withinWorkingHours && !isHoliday;
    }

    public String startMode(Long id) {
        if (!isWorkingTime()) {
            return "Сейчас не рабочее время";
        }
        if (microwave.isNeedMaintenance()) {
            return "Микроволновка нуждается в обслуживании";
        }

        Mode mode = modesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Режим не найден"));

        historyService.createRecord(mode);

        microwave.setTimeUsage(microwave.getTimeUsage() + mode.getDuration());
        microwave.setCountUsage(microwave.getCountUsage() + 1);

        if (microwave.getTimeUsage() > 1000) {
            microwave.setNeedMaintenance(true);
        }

        return "Микроволновка запущена в режиме: " + mode.getName();
    }

    public String repairMicrowave() {
        microwave.setNeedMaintenance(false);
        microwave.setTimeUsage(0);
        return "Микроволновка была отремонтирована";
    }

    public String getInfo() {
        return "Микроволновка была запущена " + microwave.getCountUsage()
                + " раз, время эксплуатации - " + microwave.getTimeUsage();
    }
}
