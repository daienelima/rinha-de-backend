package com.rinha.de.backend.convert;

import com.rinha.de.backend.dto.RequestDto;
import com.rinha.de.backend.exceptions.ValidationException;
import com.rinha.de.backend.model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RequestConverter {
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    public static Pessoa convert(RequestDto requestDto){
        return Pessoa.builder()
                .nascimento(getDate(requestDto.getNascimento()))
                .apelido(getStringValue(requestDto.getApelido()))
                .nome(getStringValue(requestDto.getNome()))
                .stack(getStack(requestDto.getStack()))
                .build();
    }

    private static String getDate(String date){
        if(!isValidDate(date)){
            throw new ValidationException("O campo Data deve esta no formato yyyy-MM-dd");
        }
        return date;
    }

    private static String getStringValue(String name){
        if(isNumeric(name)){
            throw new ValidationException("O campo Nome nao deve ser numerico");
        }
        return name;
    }

    private static String getStack(ArrayList<String> stacks){
        var stacksValid = new ArrayList<String>();
        if(stacks != null){
            stacks.forEach(s -> {
                if(isNumeric(s) ){
                    throw new ValidationException("O campo Stack nao deve ser numerico");
                }
                if(s.length() > 32){
                    throw new ValidationException("O campo Stack nao deve ser maior que 32");
                }
                stacksValid.add(s);
            });
        }
        return stacksValid.toString()
                .replace("[", "").replace("]", "");
    }

    private static boolean isNumeric(String value) {
        return value.chars().allMatch(Character::isDigit);
    }

    private static boolean isValidDate(String date){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
            LocalDate d = LocalDate.parse(date, formatter);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
