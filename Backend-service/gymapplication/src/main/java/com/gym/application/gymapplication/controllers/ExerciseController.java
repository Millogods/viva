package com.gym.application.gymapplication.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import com.gym.application.gymapplication.entities.Exercise;
import com.gym.application.gymapplication.services.ExerciseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            List<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Exercise exercise = new Exercise();
                exercise.setCategory(csvRecord.get("Category"));
                exercise.setBodySection(csvRecord.get("BodySection"));
                exercise.setEquipment(csvRecord.get("Equipment"));
                exercise.setName(csvRecord.get("Name"));
                exercise.setPrimaryMuscle(csvRecord.get("PrimaryMuscle"));
                exercise.setSecondaryMuscle(csvRecord.get("SecondaryMuscle"));
                exercise.setExerciseId(Integer.parseInt(csvRecord.get("exerciseId")));
                exercise.setExerciseType(csvRecord.get("exerciseType"));
                exercise.setCreatedOn(new Date());
                exercise.setVideoLink(csvRecord.get("videoLink"));
                exercise.setPictureLink(csvRecord.get("pictureLink"));
                exerciseService.createExercise(exercise);
            }
            csvParser.close();
            return new ResponseEntity<>("CSV file uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Could not upload CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

