package io.openvidu.recording.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.openvidu.recording.java.entity.Gallery;
@Repository
public interface GalleryRepository extends JpaRepository<Gallery,Integer> {


}
