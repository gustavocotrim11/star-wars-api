package com.letscode.starwarsapi.models;

import com.letscode.starwarsapi.models.unums.GenderEnum;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private UUID id;
    private final String name;
    private final Integer age;
    private final GenderEnum gender;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization_id", referencedColumnName = "id")
    private LocalizationModel localization;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryModel inventory;

}
