package com.huysor.projectschool.mapping;


import com.huysor.projectschool.entity.Register;
import com.huysor.projectschool.services.StudentServices;
import org.mapstruct.Mapper;
import com.huysor.projectschool.dto.register.RegisterCreateDTO;
import org.mapstruct.Mapping;


@Mapper(uses ={StudentServices.class},componentModel = "spring")
public interface RegisterMapper {
    @Mapping(target = "students",source = "stuId")
    Register toRegister(RegisterCreateDTO registerCreateDTO);

}
