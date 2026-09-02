package com.rajtechnologies.springbootdatajpa.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorMessage {
    private String status;
    private String message;
    private String path;
}
