package com.youthhomelessnessproject.academicsuccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// TODO make sure this is implemented correctly
public class AnswersDTO {
    String[] answers = new String[20];
}
