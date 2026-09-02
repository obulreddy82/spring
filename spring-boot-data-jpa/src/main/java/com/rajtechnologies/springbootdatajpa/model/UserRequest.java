package com.rajtechnologies.springbootdatajpa.model;

import com.rajtechnologies.springbootdatajpa.entity.User;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {

    private List<User> users;
}
