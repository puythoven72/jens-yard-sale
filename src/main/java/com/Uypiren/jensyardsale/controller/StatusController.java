package com.Uypiren.jensyardsale.controller;


import com.Uypiren.jensyardsale.model.selections.SalesStatus;
import com.Uypiren.jensyardsale.repository.SalesStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items/status")
@CrossOrigin(origins = "http://localhost:3000")
public class StatusController {
    @Autowired
    private SalesStatusRepository statusRepository;



    @GetMapping
    public List<SalesStatus> getAllSalesStatus(){
        System.out.println("GETTING ALL SALES STATUS SIR");
        return statusRepository.findAll();
    }

}
