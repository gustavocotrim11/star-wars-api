package com.letscode.starwarsapi.controllers;

import com.letscode.starwarsapi.dtos.InventoryDto;
import com.letscode.starwarsapi.dtos.NegotiateDTO;
import com.letscode.starwarsapi.models.RebelModel;
import com.letscode.starwarsapi.models.unums.ItemPointEnum;
import com.letscode.starwarsapi.services.InventoryService;
import com.letscode.starwarsapi.services.RebelService;
import com.letscode.starwarsapi.services.exceptions.TraitorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/negotiate")
@Api(value = "Report API REST")
public class NegotiateController {
    private final InventoryService inventoryService;
    private final RebelService rebelService;

    public NegotiateController(InventoryService inventoryService, RebelService rebelService) {
        this.inventoryService = inventoryService;
        this.rebelService = rebelService;
    }

    @ApiOperation(value = "Negotiate items ")
    @PostMapping("/{donorId}/negotiateTo/{receiverId}")
    public ResponseEntity<?> negotiateItems(@PathVariable UUID donorId, @PathVariable UUID receiverId,
                                            @RequestBody NegotiateDTO negotiateDTO) {

        try {

            inventoryService.trade(donorId, receiverId, negotiateDTO);
            return new ResponseEntity<>("Successful negotiation", HttpStatus.OK);
        } catch (TraitorException e) {

            return new ResponseEntity<>("A traitor cannot trade resources with other rebels.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
