package com.r2s.model;

import com.r2s.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    //

    public static UserModel transform(User entity) {
        return UserModel.builder()
                .id(entity.getUser_id())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getLastName())
                .build();
    }
}