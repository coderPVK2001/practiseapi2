package com.practiseapi2.service;

import com.practiseapi2.controller.Exception.ResourceNotFoundException;
import com.practiseapi2.controller.Exception.UpdateIdNotFoundException;
import com.practiseapi2.dto.CricketersDto;
import com.practiseapi2.entity.Cricketers;
import com.practiseapi2.repository.CricketersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CricketersServiceImpl implements CricketersService{

    private CricketersRepository crepo;

    public CricketersServiceImpl(CricketersRepository crepo) {
        this.crepo = crepo;
    }

    public Cricketers DtoToEntity( CricketersDto dto){

        Cricketers cricEntity =new Cricketers();

        cricEntity.setStats(dto.getStats());
        cricEntity.setCountryName(dto.getCountryName());
        cricEntity.setPlayerName(dto.getPlayerName());

        Cricketers saved = crepo.save(cricEntity);

        return saved;
    }

    public CricketersDto EntityToDto( Cricketers cEntity){

        CricketersDto cdto= new CricketersDto();

        cdto.setId(cEntity.getId());
        cdto.setCountryName(cEntity.getCountryName());
        cdto.setStats(cEntity.getStats());
        cdto.setPlayerName(cEntity.getPlayerName());

        return cdto;
    }

    //hello
//    -----------------implementations starts here------------->

    @Override
    public CricketersDto addNewData(CricketersDto dto) {

        Cricketers cricEntity = DtoToEntity(dto);

        CricketersDto cricDto = EntityToDto(cricEntity);

        return cricDto;
    }

    @Override
    public void delete(int cid) {

        Optional<Cricketers> opt = crepo.findById(cid);

        if(opt.isPresent()){
            crepo.deleteById(cid);
        }
        else{
            throw new ResourceNotFoundException("user id not found for given id:-"+ cid);
        }
    }

    @Override
    public List<CricketersDto> listOfDetails(int pageNo, int pageSize123, String sortBy, String sortDir) {

//        PageRequest pg = PageRequest.ofSize(pageSize123);                    //for only pagination/size of page
//        PageRequest pg = PageRequest.of(pageNo, pageSize123);
//        PageRequest pg = PageRequest.of(pageNo, pageSize123, Sort.by(sortBy));
//        PageRequest pg = PageRequest.of(pageNo, pageSize123, Sort.Direction.DESC, sortBy);

        Pageable pg=null;

        if(sortDir.equalsIgnoreCase("asc")){
            pg = PageRequest.of(pageNo,pageSize123, Sort.by(sortBy).ascending());
        } else if (sortDir.equalsIgnoreCase("desc")) {
            pg= PageRequest.of(pageNo,pageSize123, Sort.by(sortBy).descending());
        }

        Page<Cricketers> pObj = crepo.findAll(pg);
        List<Cricketers> cricList = pObj.getContent();

        List<CricketersDto> dtolist = cricList.stream().map(x -> EntityToDto(x)).collect(Collectors.toList());

        return dtolist;
    }

    @Override
    public CricketersDto updateDetails(int id, CricketersDto cdto) {

        Optional<Cricketers> opt = crepo.findById(id);

        if(opt.isPresent()){

//            cdto.setId(id);
//
//            Cricketers entity = DtoToEntity(cdto);

            Cricketers cric = opt.get();
            cric.setPlayerName(cdto.getPlayerName());
            cric.setStats(cdto.getStats());
            cric.setCountryName(cdto.getCountryName());
            Cricketers saved = crepo.save(cric);

            CricketersDto newDto = EntityToDto(saved);
            return newDto;
        }
        else {
            throw new UpdateIdNotFoundException("given id for update details not found:--> "+id);
        }
    }

}
