package com.letscode.starwarsapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "tb_inventories")
public class InventoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
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
