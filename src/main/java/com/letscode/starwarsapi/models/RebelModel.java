package com.letscode.starwarsapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "tb_rebels")
public class RebelModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private final UUID id;
    private final String name;
    private final Integer age;
    private final String gender;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization_id", referencedColumnName = "id")
    private LocalizationModel localization;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryModel inventory;

}
