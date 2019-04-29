package com.tim3.Log.models;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Log")
public class Log {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private String action;

    @Column
    private String description;

    @Column
    @CreationTimestamp
    private LocalDateTime creationTime;

    public Log(){}

    public Log(String tableName, String action, String description){
        this.tableName = tableName;
        this.action = action;
        this.description = description;
    }
}
