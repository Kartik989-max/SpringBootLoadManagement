package com.example.demo.service;

import com.example.demo.model.Load;
import com.example.demo.repository.LoadRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {

    private final LoadRepository loadRepository;

    public LoadService(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    public Load createLoad(Load load) {
        load.setLoadId(UUID.randomUUID()); 
        return loadRepository.save(load);
    }

    public List<Load> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    public Optional<Load> getLoadById(UUID loadId) {
        return loadRepository.findById(loadId);
    }

    public Load updateLoad(UUID loadId, Load newLoad) {
        return loadRepository.findById(loadId).map(existingLoad -> {
            existingLoad.setFacility(newLoad.getFacility());
            existingLoad.setProductType(newLoad.getProductType());
            existingLoad.setTruckType(newLoad.getTruckType());
            existingLoad.setNoOfTrucks(newLoad.getNoOfTrucks());
            existingLoad.setWeight(newLoad.getWeight());
            existingLoad.setComment(newLoad.getComment());
            existingLoad.setShipperId(newLoad.getShipperId());
            existingLoad.setDate(newLoad.getDate());
            return loadRepository.save(existingLoad);
        }).orElseThrow(() -> new RuntimeException("Load not found"));
    }

    @Transactional
public void deleteLoad(UUID loadId) {
    Load load = loadRepository.findById(loadId)
            .orElseThrow(() -> new RuntimeException("Load not found with id: " + loadId));
    loadRepository.delete(load);
}
}
