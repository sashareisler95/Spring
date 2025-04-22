package com.demo.controller;

import com.demo.repository.Medication;
import com.demo.service.MedicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/medications")
public class MedicationController {
    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public List<Medication> getAllMedication(){
        return medicationService.findAllParam();

    }

    @PostMapping("/create")
    public void createMedication(@RequestBody Medication medication){
        medicationService.create(medication);
    }

    @GetMapping("/dates")
    public String getDates(@RequestParam String name){
        return  "Last date: " + medicationService.getLastDate(name) +
                " Next date: " + medicationService.getNextDate(name);
    }

    @PutMapping("/dates")
    public void setDates(@RequestBody String newLastDate, String name){
        medicationService.setLastDate(name, newLastDate);
    }

    @GetMapping("/interval")
    public Long getInterval(@RequestBody String name){
        return medicationService.getInterval(name);
    }

    @PutMapping("/interval")
    public void setInterval(@RequestBody Long interval, String name){
        medicationService.setInterval(interval, name);
    }


}