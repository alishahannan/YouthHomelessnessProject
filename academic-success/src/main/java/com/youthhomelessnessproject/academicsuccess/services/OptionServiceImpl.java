package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Option;
import com.youthhomelessnessproject.academicsuccess.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    // Runtime constructor-based injection of OptionRepository dependency
    private final OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option getOptionById(Long id) {
        return optionRepository.findOptionById(id);
    }

    @Override
    public List<Option> getOptionsByQuestionId(Long id) {
        return optionRepository.findOptionsByQuestionId(id);
    }

    @Override
    public void deleteOptionById(Long id) {
        optionRepository.deleteOptionById(id);
    }
}
