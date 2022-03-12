package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/traitorsPercentage")
    public ResponseEntity<Long> traitorsPercentage(){
        long percentage = reportService.traitorsPercentage();
        return ResponseEntity.ok().body(percentage);
    }

    @GetMapping(value = "/rebelsPercentage")
    public ResponseEntity<Long> rebelsPercentage(){
        long percentage = reportService.rebelsPercentage();
        return ResponseEntity.ok().body(percentage);
    }

}
