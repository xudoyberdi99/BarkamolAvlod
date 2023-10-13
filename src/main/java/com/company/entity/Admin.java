package com.company.payload;

import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin extends AbstractEntity implements UserDetails {
    private String username;
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
