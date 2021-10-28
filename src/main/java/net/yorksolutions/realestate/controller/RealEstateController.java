package net.yorksolutions.realestate.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.realestate.model.RealEstate;
import net.yorksolutions.realestate.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/realestate")
@RestController
public class RealEstateController {
    @Autowired
    RealEstateRepository realEstateRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    String getAllRealEstate() throws JsonProcessingException {
        return objectMapper.writeValueAsString(realEstateRepository.findAll());
    }

    @GetMapping("/search")
    String getRealEstateSearch (@RequestParam("fname") String fname) throws JsonProcessingException {
        List<RealEstate> realEstateSearch = (List<RealEstate>) realEstateRepository.findAll();
        System.out.println(fname);
        realEstateSearch = realEstateSearch.stream().filter(row -> row.getFname().equals(fname)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(realEstateSearch);
    }

    @GetMapping("/getByRowAmount")
    String getRealEstateByRows(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<RealEstate> realEstateList = (List<RealEstate>) realEstateRepository.findAll();
        realEstateList = realEstateList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(realEstateList);
    }

    @PostMapping("/add")
    String postRealEstate(@RequestBody String body) {
        try {
            RealEstate realEstate = objectMapper.readValue(body, RealEstate.class);
            realEstateRepository.save(realEstate);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        return "success";
    }


}


