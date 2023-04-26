package com.integrador.client.Modules.ClientFactory;

import com.integrador.client.Modules.Client;

public interface ClientFactory {
    public Client createClient(Integer dni, String nameClient, String lastNameClient, String phoneClient, String emailClient, String residencyAddressClient, String cityLocationClient);
}
