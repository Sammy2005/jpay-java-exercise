package com.jumia.jpay.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Customer details table
 *
 * @author Samuel Mbugua
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    /**
     * Primary key field
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * Customer name
     */
    @Column(name = "name")
    private String name;

    /**
     * Customer phone number
     */
    @Column(name = "phone")
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
