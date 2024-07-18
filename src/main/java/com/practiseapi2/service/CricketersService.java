package com.practiseapi2.service;

import com.practiseapi2.dto.CricketersDto;

import java.util.List;

public interface CricketersService {

    CricketersDto addNewData(CricketersDto dto);

    void delete(int cid);

    List<CricketersDto> listOfDetails(int pageNo, int pageSize123, String sortBy, String sortDir);

    CricketersDto updateDetails(int id, CricketersDto cdto);
}

