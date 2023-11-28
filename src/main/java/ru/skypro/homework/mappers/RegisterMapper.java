package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.models.Register;

public class RegisterMapper {

    public static Register toRegister(RegisterDto registerDto){
        if(registerDto == null){
            throw new NullPointerException("Tried to map null in Register");
        }

        Register register = new Register();

        register.setUsername(registerDto.getUsername());
        register.setPassword(registerDto.getPassword());
        register.setFirstName(registerDto.getFirstName());
        register.setLastName(registerDto.getLastName());
        register.setPhone(registerDto.getPhone());
        if(registerDto.getRole() != null){
            register.setRole(registerDto.getRole());
        }

        return register;
    }

    public static RegisterDto fromRegister(Register register){
        if(register == null){
            throw new NullPointerException("Tried to map null in RegisterDto");
        }

        RegisterDto registerDto = new RegisterDto();

        registerDto.setUsername(register.getUsername());
        registerDto.setPassword(register.getPassword());
        registerDto.setFirstName(register.getFirstName());
        registerDto.setLastName(register.getLastName());
        registerDto.setPhone(register.getPhone());
        registerDto.setRole(register.getRole());

        return registerDto;
    }
}
