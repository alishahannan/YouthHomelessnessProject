package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Address;
import com.youthhomelessnessproject.academicsuccess.models.Option;

import java.util.List;

public interface OptionService {

    // CREATE
    Option saveOption(Option option);

    // READ
    Option getOptionById(Long id);

    List<Option> getOptionsByQuestionId(Long id);

    // DELETE
    void deleteOptionById(Long id);

}
