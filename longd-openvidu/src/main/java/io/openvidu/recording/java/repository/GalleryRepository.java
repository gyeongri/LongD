package io.openvidu.recording.java.repository;

import io.openvidu.recording.java.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery,Integer> {
}
