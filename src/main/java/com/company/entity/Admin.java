package com.company.entity;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin extends AbstractEntity implements UserDetails {
    @NotBlank(message = "username bush bulmasin")
    @Column(nullable = false)
    private String username;
    @NotBlank(message = "password bush bulmasin")
    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    private boolean enabled=true;

    private boolean AccountNonLocked=true;

    private boolean AccountNonExpired=true;

    private boolean CredentialsNonExpired=true;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
