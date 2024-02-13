package com.longd.longd.gallery.db.entity;

import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.plan.db.entity.Plan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPLE_LIST_ID")
    private CoupleList coupleList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLERY_CATEGORY_ID")
    private GalleryCategory galleryCategory;

    private LocalDate createDate;
    private String place;
    private String tag;
    private String pathUrl;
    private String title;
    private String content;
    private LocalDateTime date;


    @Transient
    private transient int size;

}
