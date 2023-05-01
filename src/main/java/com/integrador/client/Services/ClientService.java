package com.integrador.client.Services;

import com.integrador.client.DTO.ClientDTO;
import com.integrador.client.Exception.ClientNotFoundException;
import com.integrador.client.Exception.HandlerResponseException;
import com.integrador.client.Modules.Client;
import com.integrador.client.Modules.ClientFactory.ClientFactoryImp;
import com.integrador.client.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.StringJoiner;


@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientFactoryImp clientFactoryImp;

    public ClientDTO create(ClientDTO client) throws HandlerResponseException {
        try {
            if (anyFieldIsNull(client)) {
                throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "The following fields are required: " + missingFields(client));
            }
            if (clientRepository.existsById(client.getDni())) {
                throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "DNI isn't available. ");
            }
            Client clientTrue = new Client(client.getDni(),client.getNameClient(),client.getLastNameClient(),client.getPhoneClient(),client.getEmailClient(),client.getResidencyAddressClient(),client.getCityLocationClient());
            clientRepository.save(clientTrue);
            return client;
        } catch (HandlerResponseException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerResponseException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private boolean anyFieldIsNull(ClientDTO client) {
        return client.getDni() == null ||
                !StringUtils.hasText(client.getNameClient()) ||
                !StringUtils.hasText(client.getLastNameClient()) ||
                !StringUtils.hasText(client.getPhoneClient()) ||
                !StringUtils.hasText(client.getEmailClient()) ||
                !StringUtils.hasText(client.getResidencyAddressClient()) ||
                !StringUtils.hasText(client.getCityLocationClient());
    }

    private String missingFields(ClientDTO client) {
        StringJoiner joiner = new StringJoiner(", ");
        if (client.getDni() == null) joiner.add("DNI");
        if (!StringUtils.hasText(client.getNameClient())) joiner.add("Name");
        if (!StringUtils.hasText(client.getLastNameClient())) joiner.add("Last Name");
        if (!StringUtils.hasText(client.getPhoneClient())) joiner.add("Phone");
        if (!StringUtils.hasText(client.getEmailClient())) joiner.add("Email");
        if (!StringUtils.hasText(client.getResidencyAddressClient())) joiner.add("Residency Address");
        if (!StringUtils.hasText(client.getCityLocationClient())) joiner.add("City Location");
        return joiner.toString();
    }

    public Optional<ClientDTO> updateClient(Integer dni, @RequestBody ClientDTO clientUpdate) {
        if (anyFieldIsNull(clientUpdate)) {
            throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "The following fields are required: " + missingFields(clientUpdate));
        }
        Optional<Client> client = clientRepository.findById(dni);
        if (client.isPresent()) {
            Client clientToUpdate = client.get();
            clientToUpdate.setNameClient(clientUpdate.getNameClient());
            clientToUpdate.setLastNameClient(clientUpdate.getLastNameClient());
            clientToUpdate.setPhoneClient(clientUpdate.getPhoneClient());
            clientToUpdate.setEmailClient(clientUpdate.getEmailClient());
            clientToUpdate.setResidencyAddressClient(clientUpdate.getResidencyAddressClient());
            clientToUpdate.setCityLocationClient(clientUpdate.getCityLocationClient());
            Client updatedClient = clientRepository.save(clientToUpdate);
            ClientDTO updatedClientDTO = new ClientDTO(updatedClient.getDni(), updatedClient.getNameClient(),
                    updatedClient.getLastNameClient(), updatedClient.getPhoneClient(), updatedClient.getEmailClient(),
                    updatedClient.getResidencyAddressClient(), updatedClient.getCityLocationClient());
            return Optional.of(updatedClientDTO);
        }
        throw new ClientNotFoundException(HttpStatus.NOT_FOUND, "Client with DNI: " + dni + " cannot be found.");
    }


    public Boolean deleteClient(Integer dni){
        Optional<Client> client = clientRepository.findById(dni);
        if (client.isPresent()) {
            clientRepository.delete(client.get());
            return true;
        } else {
            throw new ClientNotFoundException(HttpStatus.NOT_FOUND, "Client with DNI: " + dni + " cannot be found.");
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