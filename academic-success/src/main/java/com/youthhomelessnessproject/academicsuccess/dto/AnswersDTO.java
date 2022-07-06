package com.youthhomelessnessproject.academicsuccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersDTO {
    Integer[] answers = new Integer[50];
}
