package com.integrador.client;

import com.integrador.client.DTO.ClientDTO;
import com.integrador.client.Exception.ClientNotFoundException;
import com.integrador.client.Exception.HandlerResponseException;
import com.integrador.client.Modules.Client;
import com.integrador.client.Modules.ClientFactory.ClientFactoryImp;
import com.integrador.client.Repository.ClientRepository;
import com.integrador.client.Services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClientServiceTest {
    ClientRepository clientRepository;
    ClientFactoryImp clientFactory;
    ClientService clientService;
    @Before
    public void setUp(){
        this.clientRepository = mock(ClientRepository.class);
        this.clientFactory = new ClientFactoryImp();
        this.clientService = new ClientService(clientRepository, clientFactory);
    }

    @Test
    public void validateCreateClient(){
        ClientDTO client = new ClientDTO(3,"Brian","Villegas","3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        Client clientTrue = new Client(client.getDni(),client.getNameClient(),client.getLastNameClient(),client.getPhoneClient(),client.getEmailClient(),client.getResidencyAddressClient(),client.getCityLocationClient());
        ClientDTO client1 = clientService.create(client);
        assertTrue((client.getNameClient() != null && client.getLastNameClient() != null && client.getDni() instanceof Integer));
    }

    @Test(expected = HandlerResponseException.class)
    public void validationDNI(){
        //Arrange
        ClientDTO client = new ClientDTO(null,"Brian","Villegas","3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        Client clientTrue = new Client(client.getDni(),client.getNameClient(),client.getLastNameClient(),client.getPhoneClient(),client.getEmailClient(),client.getResidencyAddressClient(),client.getCityLocationClient());
        ClientDTO response = this.clientService.create(client);
        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> {
            clientService.create(client);
        });

    }

    @Test(expected = HandlerResponseException.class)
    public void validationNameAndLastName(){
        //Arrange
        ClientDTO client = new ClientDTO(3,null,null,"3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        ClientDTO response = this.clientService.create(client);
        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> {
            clientService.create(client);
        });

    }

    @Test
    public void testResearchByIdSuccess(){
        // Arrange
        int dni = 5;
        ClientDTO client = new ClientDTO(dni,"Brian","Villegas","3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        Client clientTrue = new Client(client.getDni(),client.getNameClient(),client.getLastNameClient(),client.getPhoneClient(),client.getEmailClient(),client.getResidencyAddressClient(),client.getCityLocationClient());
        when(clientRepository.findById(dni)).thenReturn((Optional<Client>) Optional.of(clientTrue));

        // Act
        Optional<Client> result = clientService.researchById(dni);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni().intValue());
        assertEquals(client.getNameClient(), result.get().getNameClient());
        assertEquals(client.getLastNameClient(), result.get().getLastNameClient());
        assertEquals(client.getPhoneClient(), result.get().getPhoneClient());
        assertEquals(client.getEmailClient(), result.get().getEmailClient());
        assertEquals(client.getResidencyAddressClient(), result.get().getResidencyAddressClient());
        assertEquals(client.getCityLocationClient(), result.get().getCityLocationClient());
    }

    @Test
    public void testGetCustomer(){
        int dni = 7;
        ClientDTO client = new ClientDTO(dni,"Brian","Villegas","3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        Client clientTrue = new Client(client.getDni(),client.getNameClient(),client.getLastNameClient(),client.getPhoneClient(),client.getEmailClient(),client.getResidencyAddressClient(),client.getCityLocationClient());
        when(clientRepository.findById(dni)).thenReturn(Optional.of(clientTrue));

        Optional<ClientDTO> result = clientService.getCustomer(dni);

        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni().intValue());
        assertEquals(client.getNameClient(), result.get().getNameClient());
        assertEquals(client.getLastNameClient(), result.get().getLastNameClient());
        assertEquals(client.getPhoneClient(), result.get().getPhoneClient());
        assertEquals(client.getEmailClient(), result.get().getEmailClient());
        assertEquals(client.getResidencyAddressClient(), result.get().getResidencyAddressClient());
        assertEquals(client.getCityLocationClient(), result.get().getCityLocationClient());


    }

//    @Test
//    public void testDeleteByIdTrue() {
//        Integer dni = 6;
//        Client client = new Client(dni, "Brian", "Villegas", "3192621119", "sbrianpro@gmail.com", "Carrera 41", "Medellin");
//        when(clientRepository.findById(dni)).thenReturn(Optional.of(client));
//        clientRepository.save(client);
//
//        Optional<Client> foundClient = clientRepository.findById(dni);
//        assertNotNull(foundClient);
//
//        Boolean isDeleted = clientService.deleteClient(dni);
//        assertTrue(isDeleted);
//    }
    @Test
    public void testDeleteByIdFalse() {
        Integer dni = 6;
        Integer num = 3;
        Client client = new Client(dni, "Brian", "Villegas", "3192621119", "sbrianpro@gmail.com", "Carrera 41", "Medellin");
        when(clientRepository.findById(num)).thenReturn(Optional.of(client));
        clientRepository.save(client);

        Optional<Client> foundClient = clientRepository.findById(num);
        assertNotNull(foundClient);

        assertThrows(ClientNotFoundException.class, () -> clientService.deleteClient(dni));
    }
    @Test
    public void testUpdateClient() {
        int dni = 3;
        Client client = new Client(dni,"Brian","Villegas","3192621119","sbrianpro@gmail.com","Carrera 41", "Medellin");
        ClientDTO update = new ClientDTO(dni,"Steven","Java","31266805083","sStevenprog@gmail.com","Carrera Java", "Bogota");
        when(clientRepository.findById(dni)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Optional<Client> result = clientService.researchById(dni);

        Optional<ClientDTO> newUpdate = clientService.updateClient(dni, update);

        assertTrue(result.isPresent());
        assertTrue(newUpdate.isPresent());
        assertEquals(dni, newUpdate.get().getDni().intValue());
        assertEquals(update.getNameClient(), newUpdate.get().getNameClient());
        assertEquals(update.getLastNameClient(), newUpdate.get().getLastNameClient());
        assertEquals(update.getPhoneClient(), newUpdate.get().getPhoneClient());
        assertEquals(update.getEmailClient(), newUpdate.get().getEmailClient());
        assertEquals(update.getResidencyAddressClient(), newUpdate.get().getResidencyAddressClient());
        assertEquals(update.getCityLocationClient(), newUpdate.get().getCityLocationClient());
    }



}
