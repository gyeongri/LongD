package io.openvidu.recording.java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(name="type")
    private int type;
    @Column(name = "title")
    private String title;

}