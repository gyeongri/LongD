package com.longd.longd.gallery.service;


import com.longd.longd.gallery.db.dto.GallerySaveDto;
import com.longd.longd.gallery.db.entity.Gallery;

import java.util.List;

public interface GalleryService {

    public List<Gallery> getGalleryCategoryName(int coupleListId, int _limit, int _page, String categoryName, String _sort, String _order, String id_like);
    public List<Gallery> getGalleryList(int coupleListId, int _limit, int _page, String _sort, String _order, String id_like);
    public Gallery getGallery(int id);
    public List<Gallery> getGalleryListByPlanId(int planId);
    public List<Gallery> getGalleryMovieList(int _limit, int _page, String _sort, String _order);
    public boolean setGallery(List<GallerySaveDto> gallerySaveDto);
    public boolean deleteGallery(int[] id);
    public String modifyDeletePlanId(int[] id);
}
