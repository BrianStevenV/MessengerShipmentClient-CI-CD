package com.integrador.client.Modules;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Client")
@ApiModel(description ="this model represent the Client data.")
public class Client implements Serializable {
    @ApiModelProperty(value = "DNI Client", example ="15323")
    @Id
    @Column(name = "Dni_Client")
    private Integer dni;
    @ApiModelProperty(value = "Name Client", example ="Brian")
    @Column(name = "Name_Client")
    private String nameClient;
    @ApiModelProperty(value = "Surname Client", example ="Luz")
    @Column(name = "LastName_Client")
    private String lastNameClient;
    @ApiModelProperty(value = "Phone Client", example ="3005004005")
    @Column(name = "Phone_Client")
    private String phoneClient;
    @ApiModelProperty(value = "Email Client", example ="brian@gmail.com")
    @Column(name = "Email_Client")
    private String emailClient;
    @ApiModelProperty(value = "Residency Address Client", example ="Street 95")
    @Column(name = "ResidencyAddress_Client")
    private String residencyAddressClient;
    @ApiModelProperty(value = "City Location Client", example ="Madrid")
    @Column(name = "CityLocation_Client")
    private String cityLocationClient;
}
