package com.sd.sistemasd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private boolean vigencia;

    public UserEntity(int id) {
        this.id = id;
    }

    public UserEntity(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {this.password = password;}

    public void setUsername(String username) {this.username = username;}

    public void setVigencia(boolean vigencia) {this.vigencia = vigencia;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return vigencia;
    }
}
