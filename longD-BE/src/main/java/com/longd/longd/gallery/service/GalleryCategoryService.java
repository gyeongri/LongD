package com.longd.longd.gallery.service;



import com.longd.longd.gallery.db.entity.GalleryCategory;

import java.util.List;

public interface GalleryCategoryService {

    public List<String> getGalleryCategory(int coupleListId);
    public boolean setGalleryCategory(GalleryCategory galleryCategory);
    public boolean deleteGalleryCategory(int id);
}
