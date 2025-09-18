package com.gameclub.gameclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gameclub.gameclub.dto.CollectionDto;
import com.gameclub.gameclub.model.CollectionsDaily;
import com.gameclub.gameclub.services.CollectionsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionsService collectionsService;

    @PostMapping("/save")
    public CollectionsDaily saveCollection(@RequestBody CollectionDto dto) {
        CollectionsDaily collection = new CollectionsDaily();
        collection.setAmount(dto.getAmount());
        collection.setDate(dto.getDate());
        return collectionsService.create(collection);
    }

    @GetMapping("/all")
    public List<CollectionsDaily> viewAllCollections() {
        return collectionsService.findAll();
    }

    @GetMapping("/{id}")
    public CollectionsDaily getCollectionById(@PathVariable String id) {
        return collectionsService.findById(id);
    }
}
