package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryUploadRequest;
import com.example.spring08.dto.GalleryViewResponse;

public interface GalleryService {
	public List<GalleryDto> getGalleryList();
	public void createGallery(GalleryUploadRequest galleryRequest);
	public GalleryViewResponse getGallery(int num);
}
