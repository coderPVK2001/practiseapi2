package com.practiseapi2.controller;


import com.practiseapi2.repository.CricketersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @Autowired
    private CricketersRepository crepo;

    @GetMapping("/getall")
    public ResponseEntity<?> getCricketersList(){

        return new ResponseEntity<>(crepo.findAll(), HttpStatus.OK);
    }


}

