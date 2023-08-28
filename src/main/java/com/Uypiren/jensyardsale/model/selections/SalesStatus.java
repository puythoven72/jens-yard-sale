package com.Uypiren.jensyardsale.model.selections;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "sales_status")
    private String salesStatus;


}
