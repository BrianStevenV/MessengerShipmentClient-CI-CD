package com.integrador.client.Controller;

import com.integrador.client.DTO.ClientDTO;
import com.integrador.client.Modules.Client;
import com.integrador.client.Services.ClientService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="---", description = "This is controller to access the service of Client")
public class ClientController {
    private ClientService clientService;

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created client success"),
            @ApiResponse( code = 404, message = "DNI, Name or Surnames is incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="client", notes= "this create a client and save in the database", response = Client.class)
    @PostMapping("/client")
//    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO register(@ApiParam(value = "customer object", required = true) @RequestBody ClientDTO clientCreated){
        return clientService.create(clientCreated);
    }
    //Empezamos cambiando Client a ClientDTO

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "update client success"),
            @ApiResponse( code = 404, message = "Fields incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="client", notes= "this update a client and save in the database", response = Client.class)
    @PutMapping("/client/{dni}")
    public ResponseEntity<ClientDTO> updateClient(@ApiParam(value = "DNI Client", required = true) @PathVariable Integer dni, @ApiParam(value = "Client object", required = true) @RequestBody ClientDTO clientToUpdate) {
        Optional<ClientDTO> client = clientService.updateClient(dni, clientToUpdate);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiResponses(value={
            @ApiResponse( code = 201, message = "delete client success"),
            @ApiResponse( code = 404, message = "DNI client incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="client", notes= "this delete a client and delete in the database", response = String.class)
    @DeleteMapping("/client/{dni}")
    public ResponseEntity<String> deleteClient(@ApiParam(value = "DNI Client", required = true) @PathVariable Integer dni){
        boolean success = clientService.deleteClient(dni);
        if(success){
            return ResponseEntity.ok().body("The Client with DNI: " + dni + " had been eliminated.");
        }   else{
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "Get client success"),
            @ApiResponse( code = 404, message = "DNI client incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="client", notes= "this get a client from the database", response = ClientDTO.class)
    @GetMapping("/client/{dni}")
    public Optional<ClientDTO> getClientId(@ApiParam(value = "DNI Client", required = true)@PathVariable Integer dni){
        return this.clientService.getCustomer(dni);
    }

}
