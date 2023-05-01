package com.integrador.client.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDTO {
    private Integer dni;
    private String nameClient;
    private String lastNameClient;
    private String phoneClient;
    private String emailClient;
    private String residencyAddressClient;
    private String cityLocationClient;
}
