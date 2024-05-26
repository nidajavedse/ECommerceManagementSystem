package com.apricart.task.service;

import com.apricart.task.model.Warehouse;
import com.apricart.task.repository.WarehouseRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(Warehouse warehouse) {
        logger.info("Creating warehouse: {}", warehouse);
        Warehouse createdWarehouse = warehouseRepository.save(warehouse);
        logger.info("Warehouse created successfully: {}", createdWarehouse);
        return createdWarehouse;
    }

    public Warehouse updateWarehouse(Long warehouseId, Warehouse warehouseDetails) {
        logger.info("Updating warehouse with ID {}: {}", warehouseId, warehouseDetails);
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        warehouse.setWarehouseName(warehouseDetails.getWarehouseName());
        warehouse.setLocation(warehouseDetails.getLocation());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setAvailableCapacity(warehouseDetails.getAvailableCapacity());

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);
        logger.info("Warehouse updated successfully: {}", updatedWarehouse);
        return updatedWarehouse;
    }

    public void deleteWarehouse(Long warehouseId) {
        logger.info("Deleting warehouse with ID: {}", warehouseId);
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        warehouseRepository.delete(warehouse);
        logger.info("Warehouse deleted successfully with ID: {}", warehouseId);
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        logger.info("Retrieving warehouse by ID: {}", warehouseId);
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        logger.info("Warehouse retrieved successfully by ID: {}", warehouseId);
        return warehouse;
    }

    public List<Warehouse> getAllWarehouses() {
        logger.info("Retrieving all warehouses");
        List<Warehouse> warehouses = warehouseRepository.findAll();
        logger.info("All warehouses retrieved successfully");
        return warehouses;
    }
}
