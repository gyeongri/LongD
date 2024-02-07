package com.longd.longd.gallery.service;



import com.longd.longd.gallery.db.entity.GalleryCategory;

import java.util.List;
import java.util.Map;

public interface GalleryCategoryService {

    public List<Map<String, Object>> getGalleryCategory(int coupleListId);
    public boolean setGalleryCategory(GalleryCategory galleryCategory);
    public boolean deleteGalleryCategory(int id);
}
