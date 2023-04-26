package com.integrador.client.Modules.ClientFactory;

import com.integrador.client.Modules.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientFactoryImp {
    public Client createClientImp(Integer dni, String nameClient, String lastNameClient, String phoneClient, String emailClient, String residencyAddressClient, String cityLocationClient){
        return new Client(dni, nameClient, lastNameClient, phoneClient, emailClient, residencyAddressClient, cityLocationClient);
    }
}
