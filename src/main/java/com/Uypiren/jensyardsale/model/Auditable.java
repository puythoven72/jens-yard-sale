package com.Uypiren.jensyardsale.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "created_date")
    private LocalDate creationTime;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
}
