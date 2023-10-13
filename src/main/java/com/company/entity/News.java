package com.company.entity;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class News extends AbstractEntity {
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition="TEXT", length=10485760)
    @Lob
    private String  title;
    @Lob
    @Column(columnDefinition="TEXT", length=10485760)
    private String description;
    @OneToOne(optional = false)
    private Attachment images;
}
