package com.homeautomation.model;


import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="devices")


public class Device {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false)
    private String deviceName;

    @Column(unique = true, nullable = false)
    private String uuid;

    @Column()
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime updatedAt;

    @Column(unique = true,nullable=false)
    private String macAddress;

    @Column()
    private LocalDateTime lastActive;

    @Column()
    private Long upTime; 

    @Column()
    private String powerMode;

    @Column(nullable=false)
    private String relationshipWithUser;

    @Column(nullable=false)
    private String deviceState;

    @Column()
    private String communicationType;

    @Column(nullable=false)
    private String deviceType;

   
}



