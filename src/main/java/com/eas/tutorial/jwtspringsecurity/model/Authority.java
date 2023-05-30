package com.eas.tutorial.jwtspringsecurity.model;

import com.eas.tutorial.jwtspringsecurity.util.AuthorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "authorities")
public class Authority {

    public Authority(AuthorityName name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private AuthorityName name;
}
