package com.longd.longd.gallery.service;


import com.longd.longd.gallery.db.entity.Gallery;

import java.util.List;

public interface GalleryService {

    public List<Gallery> getGalleryList(int coupleListId);
    public Gallery getGallery(int id);
    public boolean setGallery(Gallery gallery);
    public boolean deleteGallery(int id);
}
