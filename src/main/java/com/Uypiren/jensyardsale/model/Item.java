package com.Uypiren.jensyardsale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")

public class Item extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_description")
    private String description;

    @Column(name = "item_category")
    private String category;

    @Column(name = "item_condition")
    private String condition;

    @Column(name = "item_price")
    private BigDecimal price;

    @Column(name = "main_img_id")
    private long mainImageID;

    @Column(name = "sale_status")
    private String saleStatus;

    @Column(name = "is_purchased")
    private boolean isPurchased = false;

    private List<Long> allImages;


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", mainImageID=" + mainImageID +
                ", allImages=" + allImages +
                '}';
    }
}
