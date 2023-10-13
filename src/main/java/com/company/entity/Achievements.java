package com.company.entity;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Achievements extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @Lob
    private String body;

    @Lob
    private String description;

    @OneToOne
    private Attachment images;
}
