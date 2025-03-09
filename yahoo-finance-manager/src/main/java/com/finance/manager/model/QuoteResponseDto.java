package com.finance.manager.model;

import java.util.List;
import lombok.Data;

@Data
public class QuoteResponseDto {
    private List<QuoteDto> result;
    private String error;
}