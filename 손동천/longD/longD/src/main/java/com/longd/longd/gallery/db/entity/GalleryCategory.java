package com.longd.longd.gallery.db.entity;

import com.longd.longd.coupleList.db.entity.CoupleList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class GalleryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPLE_LIST_ID")
    private CoupleList coupleList;
    private String category;

}
