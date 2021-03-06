package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.models.ResourcesMeans;
import com.letscode.starwarsapi.services.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
@Api(value = "Report API REST")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "Get percentage of traitors")
    @GetMapping(value = "/traitorsPercentage")
    public ResponseEntity<Long> traitorsPercentage(){
        long percentage = reportService.traitorsPercentage();
        return ResponseEntity.ok().body(percentage);
    }

    @ApiOperation(value = "Get percentage of rebels")
    @GetMapping(value = "/rebelsPercentage")
    public ResponseEntity<Long> rebelsPercentage(){
        long percentage = reportService.rebelsPercentage();
        return ResponseEntity.ok().body(percentage);
    }

    @ApiOperation(value = "Get resources means of rebels")
    @GetMapping(value = "/rebelsResourcesMeans")
    public ResponseEntity<ResourcesMeans> rebelsResourcesMeans(){
        ResourcesMeans rebelsResourcesMeans = reportService.rebelsResourcesMeans();
        return ResponseEntity.ok().body(rebelsResourcesMeans);
    }

    @ApiOperation(value = "Get total of lost points")
    @GetMapping(value = "/lostPoints")
    public ResponseEntity<String> lostPoints(){
        String lostPoints = String.format("Points lost: %d",reportService.traitorsTotalPoints());
        return ResponseEntity.ok().body(lostPoints);
    }

}
