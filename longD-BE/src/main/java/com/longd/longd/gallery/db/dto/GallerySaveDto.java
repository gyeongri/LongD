package com.longd.longd.gallery.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GallerySaveDto {

    private Integer id;
    private Integer coupleListId;
    private Integer categoryId;
    private String pathUrl;

}