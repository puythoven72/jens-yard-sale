package com.Uypiren.jensyardsale.controller;


import com.Uypiren.jensyardsale.exception.ResourceNotFoundException;
import com.Uypiren.jensyardsale.model.Item;
import com.Uypiren.jensyardsale.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    //rest create Item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        System.out.println(item);
        System.out.println("BEFORE THE SAVE");
        return itemRepository.save(item);
    }


    //get item by id
    @GetMapping("{id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item with " + id + " cannot be found!"));
        return ResponseEntity.ok(item);
    }


    //update item by id
    @PutMapping("{id}")
    public ResponseEntity<Item> updateItemById(@PathVariable long id, @RequestBody Item itemDetails) {
        Item updateItem = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item with " + id + " cannot be found!"));
        System.out.println(itemDetails.getId());
        updateItem.setId(itemDetails.getId());
        updateItem.setName(itemDetails.getName());
        updateItem.setDescription(itemDetails.getDescription());
        updateItem.setCategory(itemDetails.getCategory());
        updateItem.setCondition(itemDetails.getCondition());
        updateItem.setPrice(itemDetails.getPrice());
        updateItem.setSaleStatus(itemDetails.getSaleStatus());
        itemRepository.save(updateItem);
        return ResponseEntity.ok(updateItem);
    }

    //delete item by id
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteItemBy(@PathVariable long id) {
        System.out.println(id + " is the id");
        ResponseEntity<Item> deleteItemEntity = getItemById(id);
        Item deleteItem = deleteItemEntity.getBody();
        itemRepository.delete(deleteItem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    //update item by id
    @PatchMapping("{id}")
    public ResponseEntity<Item> markAsSoldById(@PathVariable long id) {
        System.out.println("IN mark as sold " + id );
        Item updateItem = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item with " + id + " cannot be found!"));
        updateItem.setPurchased(true);
        updateItem.setPurchaseDate(LocalDate.now());
        updateItem.setSaleStatus("Sold");
        itemRepository.save(updateItem);
        return ResponseEntity.ok(updateItem);
    }

}
