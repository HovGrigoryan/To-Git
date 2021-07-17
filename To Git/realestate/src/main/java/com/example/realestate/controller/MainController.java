package com.example.realestate.controller;

import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingStatus;
import com.example.realestate.model.User;
import com.example.realestate.repository.ListingRepository;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.security.CurrentUser;
import org.apache.tomcat.jni.Mmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
        }
        List<Listing> feauturedList = listingRepository.findAllByListingStatus(ListingStatus.FEATURED);
        List<Listing> latestThree = listingRepository.findTop3ByOrderByIdDesc();
        modelMap.addAttribute("featuredList", feauturedList);
        modelMap.addAttribute("latestThree", latestThree);
        return "index";
    }

    @GetMapping("/search")
    public String search(ModelMap modelmap,@RequestParam("keyword") String keyword) {
        List<Listing> searchResult = listingRepository.findAllByTitleIgnoreCaseContaining(keyword);
        modelmap.addAttribute("listings",searchResult);
        return "search";
    }

    @GetMapping("/signIn")
    public String signIn(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
        }
        return "signin";
    }

    @GetMapping("/register")
    public String registerGet(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/signIn";
    }
}