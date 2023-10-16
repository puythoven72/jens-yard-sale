package com.Uypiren.jensyardsale.repository;

import com.Uypiren.jensyardsale.model.images.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageData,Long> {
   Optional<ImageData> findByName(String name);
   List<ImageData> findByItemId(long itemId);

   ImageData findByItemIdAndIsPrimary(long itemId, boolean isPrimary);
}