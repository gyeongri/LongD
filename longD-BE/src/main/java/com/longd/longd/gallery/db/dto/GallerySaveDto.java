package com.longd.longd.gallery.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GallerySaveDto {

    private Integer id;
    private LocalDate createDate;
    private Integer coupleListId;
    private Integer categoryId;
    private String pathUrl;

}