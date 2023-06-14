package com.example.demo.repo;

import com.example.demo.dto.KeyValueDTO;
import com.example.demo.model.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConverterRepository extends JpaRepository<Converter, Integer>
{


    @Query("SELECT new  com.example.demo.dto.KeyValueDTO(name as keyname,  value as keyvalue,  path as keypath , count(*) as count)" +
            " FROM Converter group by keyname, keyvalue, keypath having count(*)  > 1")
    List<KeyValueDTO> findAggregate();






}
