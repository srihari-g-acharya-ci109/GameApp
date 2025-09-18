package com.gameclub.gameclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.model.CollectionsDaily;
import com.gameclub.gameclub.repository.CollectionsRepository;

@Service
public class CollectionsService {

    @Autowired
    private CollectionsRepository repo;

    public CollectionsDaily create(CollectionsDaily collection) {
        collection.setId(null);
        return repo.save(collection);
    }

    public List<CollectionsDaily> findAll() {
        return repo.findAll();
    }

    public CollectionsDaily findById(String id) {
        Optional<CollectionsDaily> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Collection not found: " + id);
        }
        return optional.get();
    }

    public CollectionsDaily update(String id, CollectionsDaily collection) {
        Optional<CollectionsDaily> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Collection not found: " + id);
        }
        CollectionsDaily old = optional.get();
        old.setAmount(collection.getAmount());
        old.setDate(collection.getDate());
        return repo.save(old);
    }

    public boolean delete(String id) {
        Optional<CollectionsDaily> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Collection not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
