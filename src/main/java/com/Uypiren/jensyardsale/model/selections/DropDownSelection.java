package com.Uypiren.jensyardsale.model.selections;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drop_down_selections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DropDownSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "selection_value")
    private String selectionValue;
    @Column(name = "selection_type")
    private int selectionType;


}
