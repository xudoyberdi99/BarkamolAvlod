package com.company.entity;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Teacher extends AbstractEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(length = 10000)
    private String descriptionUZ;
    @Column(length = 10000)
    private String descriptionRU;

    @OneToOne
    private Attachment image;
    @ManyToOne(optional = false)
    private Course course;
}
