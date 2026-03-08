package com.url.shortener.controller;


import com.url.shortener.Service.UrlMappingService;
import com.url.shortener.Service.UserService;
import com.url.shortener.dtos.UrlMappingDTO;
import com.url.shortener.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;


@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {

    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String,String> request ,
                                                        Principal principal){

        String originalUrl = request.get("OriginalUrl");
        User user=  userService.findByUsername(principal.getName());
        UrlMappingDTO urlmappingDTO = urlMappingService.createShortUrl(originalUrl,user);
        return ResponseEntity.ok(urlmappingDTO);


    }

}
