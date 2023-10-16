package com.Uypiren.jensyardsale.repository;

import com.Uypiren.jensyardsale.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findItemsByCategory(String category);

}
