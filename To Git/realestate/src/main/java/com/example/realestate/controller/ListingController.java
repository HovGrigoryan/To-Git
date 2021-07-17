package com.example.realestate.controller;

import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingFeatures;
import com.example.realestate.repository.ListingFeatureRepository;
import com.example.realestate.repository.ListingRepository;
import com.example.realestate.security.CurrentUser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ListingController {
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private ListingFeatureRepository listingFeatureRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @GetMapping("/listing/add")
    public String addlisting(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
        }
        List<ListingFeatures> featuresList = listingFeatureRepository.findAll();
        modelMap.addAttribute("features", featuresList);
        return "addListing";
    }

    @PostMapping("/listing/add")
    public String addListingPost(@ModelAttribute Listing listing,
                                 @RequestParam("img") MultipartFile multipartFile,
                                 @RequestParam("features") List<Long> features) throws IOException {
        List<ListingFeatures> allById = listingFeatureRepository.findAllById(features);
        listing.setFeatureList(allById);
        String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File(uploadDir,picName);
        multipartFile.transferTo(file);
        listing.setPicurl(picName);
        listingRepository.save(listing);
        System.out.println(listing);
        return "redirect:/listing/add";
    }

    @GetMapping(
            value = "/listing/image",
            produces = MediaType.IMAGE_JPEG_VALUE)

    public @ResponseBody
    byte[] userImage(@RequestParam("picUrl") String picUrl) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + picUrl);
        return IOUtils.toByteArray(in);
    }

}
