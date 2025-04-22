package com.demo.service;

import com.demo.repository.Medication;
import com.demo.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final String pattern = "dd.MM.yyyy";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public void create(Medication medication){
        if (medicationRepository.findMedsByName(medication.name) == null){
            medication.nextDate = calculateNextDate(medication.lastDate, medication.interval);
            medicationRepository.save(medication);
        }
        else {
            throw new IllegalStateException("Данный вид медицинского обслуживания псины-корзины уже существует!");
        }
    }

    public String getLastDate(String name){
        if (medicationRepository.findMedsByName(name) != null){
            return medicationRepository.findLastDate(name);
        }
        else {
            return "Не существует медицинского обслуживания псины-корзины с именем - " + name;
        }
    }

    public List<Medication> findAllParam(){
        return medicationRepository.findAll();
    }


    public String getNextDate(String name){
        if (medicationRepository.findMedsByName(name) != null){
            return medicationRepository.findNextDate(name);
        }
        else {
            return "Не существует медицинского обслуживания псины-корзины с именем - " + name;
        }
    }

    public void setNextDate(String name) {
        if (medicationRepository.findMedsByName(name) != null){
            String nextDate = calculateNextDate(getLastDate(name), getInterval(name));
            medicationRepository.setNextDate(name, nextDate);
        }
        else {
            throw new IllegalStateException("Не существует медицинского обслуживания псины-корзины с именем - " + name);
        }
    }

    public void setLastDate(String name, String newDate) {
        if (medicationRepository.findMedsByName(name) != null){
            medicationRepository.setLastDate(name, newDate);
            setNextDate(name);
        }
        else {
            throw new IllegalStateException("Не существует медицинского обслуживания псины-корзины с именем - " + name);
        }
    }

    public void setInterval(Long interval, String name){
        if (medicationRepository.findMedsByName(name) != null){
            medicationRepository.setInterval(interval, name);
        }
        else {
            throw new IllegalStateException("Не существует медицинского обслуживания псины-корзины с именем - " + name);
        }
    }

    public Long getInterval(String name){
        if (medicationRepository.findMedsByName(name) != null){
            return medicationRepository.findInterval(name);
        }
        else {
            throw new IllegalStateException("Не существует медицинского обслуживания псины-корзины с именем - " + name);
        }
    }


    public String calculateNextDate(String lastDate, Long interval){
        LocalDate nextDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        if (!Objects.equals(lastDate, "")){
            LocalDate date = LocalDate.parse(lastDate, formatter);
            nextDate = date.plusDays(interval);
            return nextDate.format(formatter);
        }
        else {
            throw new IllegalStateException("Не передан интервал!");
        }
    }
}
