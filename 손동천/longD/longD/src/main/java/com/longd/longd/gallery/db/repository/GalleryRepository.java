package com.longd.longd.gallery.db.repository;

import com.longd.longd.gallery.db.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {

    public List<Gallery> findByCoupleList_Id(int id);

    public List<Gallery> findByCoupleList_IdAndFolderName(int id, String folderName);
}
