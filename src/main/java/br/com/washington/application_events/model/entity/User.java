package br.com.washington.application_events.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EnableJpaAuditing
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private Boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    private Instant deletedAt;

    public User(String username, String name, String email, String password, String phone){
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.enabled = true;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.deletedAt = null;
    }

    public User updateName(String name){
        this.name = name;
        this.updatedAt = Instant.now();
        return this;
    }

    public User updateEmail(String email){
        this.email = email;
        this.updatedAt = Instant.now();
        return this;
    }

    public User updatePassword(String password){
        this.password = password;
        this.updatedAt = Instant.now();
        return this;
    }

    public User softDelete(){
        this.enabled = false;
        this.deletedAt = Instant.now();
        return this;
    }
}
