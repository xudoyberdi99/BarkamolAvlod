package com.company.entity.template;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

//    @JoinColumn(updatable = false)
//    @CreatedBy
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User createdBy;


//    @LastModifiedBy
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User updatedBy;
}
