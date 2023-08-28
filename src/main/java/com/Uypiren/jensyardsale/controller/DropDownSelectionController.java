package com.Uypiren.jensyardsale.controller;


import com.Uypiren.jensyardsale.model.selections.DropDownSelection;
import com.Uypiren.jensyardsale.repository.DropDownSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items/drop-down-selections")
@CrossOrigin(origins = "http://localhost:3000")
public class DropDownSelectionController {
    @Autowired
    private DropDownSelectionRepository selectionRepository;



    @GetMapping
    public List<DropDownSelection> getAllSelections(){
        System.out.println("GETTING ALL SALES STATUS SIR");
        return selectionRepository.findAll();
    }

    @GetMapping("{selectionType}")
    public List<DropDownSelection> getSelectionsById(@PathVariable int selectionType){
        System.out.println("GETTING FOR "+ selectionType +" SALES STATUS SIR");
        return selectionRepository.findBySelectionType(selectionType);

    }

}
