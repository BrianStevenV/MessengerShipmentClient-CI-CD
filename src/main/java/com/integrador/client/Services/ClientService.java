package com.integrador.client.Services;

import com.integrador.client.DTO.ClientDTO;
import com.integrador.client.Exception.HandlerResponseException;
import com.integrador.client.Modules.Client;
import com.integrador.client.Modules.ClientFactory.ClientFactoryImp;
import com.integrador.client.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientFactoryImp clientFactoryImp;

    public Client create(Client client) {
        Client newClient = clientFactoryImp.createClientImp(client.getDni(), client.getNameClient(), client.getLastNameClient(), client.getPhoneClient(), client.getEmailClient(), client.getResidencyAddressClient(), client.getCityLocationClient());
        if (newClient.getDni() != null && newClient.getDni().toString().isEmpty()) {
            Optional<Client> tempCustomer = this.clientRepository.findById(client.getDni());
            if (tempCustomer.isPresent()) {
                throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "DNI is rejected in database.");
            }
        }

        if (newClient.getNameClient() != null && !newClient.getNameClient().isEmpty()
                && newClient.getLastNameClient() != null && !newClient.getLastNameClient().isEmpty()
                && newClient.getDni() != null && newClient.getDni() instanceof Integer) {
            return this.clientRepository.save(client);
            //return client;
        } else {
            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "DNI, First Name and Last Name are required");

        }
    }

    public Optional<Client> updateClient(Integer dni, @RequestBody Client clientUpdate) {
        Optional<Client> client = clientRepository.findById(dni);
        if (client.isPresent()) {
            Client clientToUpdate = client.get();
            if (clientUpdate.getNameClient() != null) {
                clientToUpdate.setNameClient(clientUpdate.getNameClient());
            }
            if (clientUpdate.getLastNameClient() != null) {
                clientToUpdate.setLastNameClient(clientUpdate.getLastNameClient());
            }
            if (clientUpdate.getPhoneClient() != null) {
                clientToUpdate.setPhoneClient(clientUpdate.getPhoneClient());
            }
            if (clientUpdate.getEmailClient() != null) {
                clientToUpdate.setEmailClient(clientUpdate.getEmailClient());
            }
            if (clientUpdate.getResidencyAddressClient() != null) {
                clientToUpdate.setResidencyAddressClient(clientUpdate.getResidencyAddressClient());
            }
            if (clientUpdate.getCityLocationClient() != null) {
                clientToUpdate.setCityLocationClient(clientUpdate.getCityLocationClient());
            }
            clientRepository.save(clientToUpdate);
            return Optional.of(clientToUpdate);
        }
        return Optional.empty();
    }

    public Boolean deleteClient(Integer dni){
        Optional<Client> client = clientRepository.findById(dni);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return true;
        } else {
            return false;
        }
    }
    public Optional<Client> researchById(int id){
        Optional<Client> customer = clientRepository.findById(id);
        if(customer.isEmpty()){

            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"Client doesn't find in our database.");
        }
        return customer;
    }

    public Optional<ClientDTO> getCustomer(int dni) {
        Optional<Client> customer =researchById(dni);
        return Optional.of(new ClientDTO(
                customer.get().getDni(),
                customer.get().getNameClient(),
                customer.get().getLastNameClient(),
                customer.get().getPhoneClient(),
                customer.get().getEmailClient(),
                customer.get().getResidencyAddressClient(),
                customer.get().getCityLocationClient()));
    }

}