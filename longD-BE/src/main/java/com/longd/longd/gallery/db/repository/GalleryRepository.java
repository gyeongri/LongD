package com.longd.longd.gallery.db.repository;

import com.longd.longd.gallery.db.entity.Gallery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {

    public List<Gallery> findByCoupleList_Id(int id, Sort sort);

    public List<Gallery> findByCoupleList_IdAndFolderName(int id, String folderName, Sort sort);

//    @Query("SELECT g FROM Gallery g ORDER BY sort DESC")
//    public List<Gallery> findByCoupleList_IdAndFolderNameOrder(int id, String folderName, @Param("sort") String sort);
}
