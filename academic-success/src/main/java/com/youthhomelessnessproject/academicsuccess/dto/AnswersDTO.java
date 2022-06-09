package com.youthhomelessnessproject.academicsuccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersDTO {
    String[] answers = new String[20];
}
