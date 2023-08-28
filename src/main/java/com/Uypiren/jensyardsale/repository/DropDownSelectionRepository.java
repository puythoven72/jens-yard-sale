package com.Uypiren.jensyardsale.repository;

import com.Uypiren.jensyardsale.model.selections.DropDownSelection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DropDownSelectionRepository extends JpaRepository<DropDownSelection, Integer> {

    List<DropDownSelection> findBySelectionType(int selectionType);
}
