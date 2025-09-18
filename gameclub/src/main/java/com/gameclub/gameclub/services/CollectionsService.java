package com.gameclub.gameclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.CollectionDto;
import com.gameclub.gameclub.model.CollectionsDaily;
import com.gameclub.gameclub.repository.CollectionsRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;

@Service
public class CollectionsService {

    @Autowired
    private CollectionsRepository collectionsRepo;

    public CollectionsDaily create(CollectionDto dto) {
        CollectionsDaily collection = new CollectionsDaily();
        collection.setAmount(dto.getAmount());
        collection.setDate(dto.getDate());
        collection.setId(null);
        return collectionsRepo.save(collection);
    }

    public List<CollectionsDaily> findAll() {
        return collectionsRepo.findAll();
    }

    public CollectionsDaily findById(String id) {
        return collectionsRepo.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Collection not found: " + id));
    }

    public CollectionsDaily update(String id, CollectionDto dto) {
        CollectionsDaily existing = findById(id);
        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        return collectionsRepo.save(existing);
    }
}
