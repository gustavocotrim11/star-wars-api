package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.services.LocalizationService;
import com.letscode.starwarsapi.services.RebelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private final RebelService rebelService;

    public ReportController(RebelService rebelService) {
        this.rebelService = rebelService;
    }


}
