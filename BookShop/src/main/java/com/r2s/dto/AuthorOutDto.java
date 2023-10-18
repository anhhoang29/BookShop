package com.r2s.dto;
import com.r2s.model.AuthorModel;
import lombok.Data;

import java.util.List;

@Data
public class AuthorOutDto {
    private List<AuthorModel> authors;
    private Integer total;
}
