package com.letscode.starwarsapi.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NegotiateDTO {

    private InventoryDto itemsToNegotiate1;
    private InventoryDto itemsToNegotiate2;

}
