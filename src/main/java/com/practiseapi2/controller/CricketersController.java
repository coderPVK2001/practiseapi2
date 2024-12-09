package com.practiseapi2.controller;

import com.practiseapi2.dto.CricketersDto;
import com.practiseapi2.entity.Cricketers;
import com.practiseapi2.repository.CricketersRepository;
import com.practiseapi2.service.CricketersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cricapi")
@CrossOrigin("http://localhost:4200/")   //now it will allow this website will get accesss to use our app otherwise they would have been blocked
public class CricketersController {

    private CricketersService cservice;
    private CricketersRepository crepo;

    public CricketersController(CricketersService cservice, CricketersRepository crepo) {
        this.cservice = cservice;
        this.crepo = crepo;
    }

    @PostMapping
    public ResponseEntity<CricketersDto> addData(@RequestBody CricketersDto dto){

        System.out.println(dto.getId());

        CricketersDto savedDto =cservice.addNewData(dto);
        System.out.println("added new data here ");

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteDetails(@RequestParam int cid){

        cservice.delete(cid);

        return new ResponseEntity<>("details deleted sucessfully!!", HttpStatus.OK);
    }

//    url :- http://localhost:8090/v1/cricapi?pageNo=1&pageSize=3
    @GetMapping
    public ResponseEntity<List<CricketersDto>> listCricketers(
            @RequestParam( name = "pageNo" ,defaultValue = "1",required = false) int pageNo,
            @RequestParam( name = "pageSize" ,defaultValue = "4",required = false) int pageSize123,
            @RequestParam( name = "sortBy" ,defaultValue = "id",required = false) String sortBy,
            @RequestParam( name = "sortDir" ,defaultValue = "asc",required = false) String sortDir
    ){

        List<CricketersDto> clist= cservice.listOfDetails(pageNo, pageSize123 ,sortBy ,sortDir);

        return new ResponseEntity<>(clist, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateDetails(@PathVariable int id,
//                                    @RequestBody CricketersDto cdto){
//
//        CricketersDto dto =cservice.updateDetails(id ,cdto);
//
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }

    @PutMapping("/{id}")           //better to use only when full entity class updation happens/needed
    public ResponseEntity<?> updateDetails(@PathVariable int id,
                                    @RequestBody Cricketers cricdto){

        Cricketers cricketers1 = crepo.findById(id).get();
        int newid=3;
//        Optional<Cricketers> optional = crepo.findById(newid);
//        Cricketers cricketers2 = optional.orElseGet(() -> cricketers1);

        cricketers1.setPlayerName(cricdto.getPlayerName());
        cricketers1.setStats(cricdto.getStats());
        cricketers1.setCountryName(cricdto.getCountryName());

        Cricketers saved = crepo.save(cricketers1);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }



}
