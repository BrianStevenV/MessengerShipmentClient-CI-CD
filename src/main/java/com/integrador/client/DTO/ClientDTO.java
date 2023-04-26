package com.integrador.client.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientDTO {
    private Integer dni;
    private String nameClient;
    private String lastNameClient;
    private String phoneClient;
    private String emailClient;
    private String residencyAddressClient;
    private String cityLocationClient;
}
