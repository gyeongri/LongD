package com.longd.longd.gallery.service;


import com.longd.longd.gallery.db.entity.Gallery;

import java.util.List;

public interface GalleryService {

    public List<Gallery> getGalleryFolderList(int coupleListId, int _limit, int _page, String folderList, String _sort, String _order, String id_like);
    public List<Gallery> getGalleryList(int coupleListId, int _limit, int _page, String _sort, String _order, String id_like);
    public Gallery getGallery(int id);
    public boolean setGallery(Gallery gallery);
    public boolean deleteGallery(int[] id);
}
