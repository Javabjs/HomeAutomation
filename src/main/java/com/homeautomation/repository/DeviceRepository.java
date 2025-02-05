package com.homeautomation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeautomation.model.Device;

@Repository

public interface DeviceRepository extends JpaRepository<Device,UUID> {
    Device findByUuid(String uuid);
}



