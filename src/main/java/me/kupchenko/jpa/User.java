package me.kupchenko.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@NamedQuery(name = "findAllUsersWithName", query = "FROM User c WHERE c.name LIKE :custName")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String name;
}
