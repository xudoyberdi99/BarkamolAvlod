package com.company.entity;
import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbstractEntity {
    private String fileName;
    private String contentType;
    private long size;
    private String systemName;
}
