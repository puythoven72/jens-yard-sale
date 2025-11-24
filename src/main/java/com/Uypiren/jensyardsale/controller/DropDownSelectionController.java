package com.Uypiren.jensyardsale.controller;


import com.Uypiren.jensyardsale.exception.ResourceNotFoundException;
import com.Uypiren.jensyardsale.model.selections.DropDownSelection;
import com.Uypiren.jensyardsale.repository.DropDownSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return selectionRepository.findAll();
    }

    @GetMapping("{selectionType}")
    public List<DropDownSelection> getSelectionsById(@PathVariable int selectionType){
        return selectionRepository.findBySelectionType(selectionType);
    }


    @PostMapping("/addNewCategory")
    public DropDownSelection addCategory(@RequestBody DropDownSelection dropDownSelection) {
        System.out.println(dropDownSelection.getSelectionValue());
        return selectionRepository.save(dropDownSelection);
    }


    @DeleteMapping("/deleteSelection/{id}")
    public ResponseEntity<Object> deleteDropDownSelectionById(@PathVariable Integer id ) {
        System.out.println("deleteting " + id);
        DropDownSelection selectionToDelete =  selectionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Selection with id of "+ id + " cannot be found"));
        selectionRepository.delete(selectionToDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
