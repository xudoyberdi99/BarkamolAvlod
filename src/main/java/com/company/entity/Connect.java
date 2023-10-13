package com.company.entity;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Connect extends AbstractEntity {
    @Column(unique = true)
   private String phoneNumber;

    @Column(unique = true)
   private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String instagramlink;

    @Column(nullable = false)
    private String telegramlink;

    @Column(nullable = false)
    private String facebooklink;

}
