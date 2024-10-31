//package com.huysor.projectschool.services.implement;
//
//import com.huysor.projectschool.entity.Register;
//import com.huysor.projectschool.exception.ApiRequestException;
//import com.huysor.projectschool.repo.RegisterRepo;
//import com.huysor.projectschool.services.RegisterServices;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@RequiredArgsConstructor
//@Service
//public class RegisterImpl implements RegisterServices {
//    private final RegisterRepo registerRepo;
//    @Override
//    public List<Register> findAll() {
//        return registerRepo.findAll();
//    }
//
//    @Override
//    public Register findById(Long id) {
//        return  registerRepo.findById(id).orElseThrow(()-> new  ApiRequestException("Register with id %d Not Found".valueOf(id)));
//    }
//
//    @Override
//    public Register save(Register register) {
//        return registerRepo.save(register);
//    }
//
//    @Override
//    public void delete(Long id) {
//    Register register =  findById(id);
//    registerRepo.delete(register);
//    }
//
//    @Override
//    public Register update(Long id, Register register) {
//        findById(id);
//        register.setId(id);
//        return registerRepo.save(register);
//    }
//}
