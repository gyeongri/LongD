package com.longd.longd.gallery.db.repository;

import com.longd.longd.gallery.db.entity.GalleryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryCategoryRepository extends JpaRepository<GalleryCategory, Integer> {

    public List<GalleryCategory> findByCoupleList_Id(int id);
    public Optional<GalleryCategory> findByCoupleList_IdAndCategory(int id, String category);
}
