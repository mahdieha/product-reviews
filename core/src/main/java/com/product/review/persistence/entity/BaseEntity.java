package com.product.review.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity<Key extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    protected Key id;
    protected Timestamp createDate;
    protected Timestamp updateDate;

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        createDate = now;
        updateDate = now;

    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Timestamp(System.currentTimeMillis());
    }
}