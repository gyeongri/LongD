package io.openvidu.recording.java.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="gallery")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="couple_list_id")
    private int coupleListId;
    @Column(name="path_url")
    private String pathUrl;
}
