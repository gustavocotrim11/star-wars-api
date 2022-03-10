package com.letscode.starwarsapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "tb_inventories")
public class InventoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull
    @Id
    private UUID id;
    @JsonIgnore
    @OneToOne(mappedBy = "inventory")
    private RebelModel rebel;
    @NonNull
    private Integer weapon;
    @NonNull
    private Integer ammo;
    @NonNull
    private Integer water;
    @NonNull
    private Integer food;
}
