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

    @Setter(AccessLevel.NONE)
    private UUID id;

    private final String name;
    private final Integer age;
    private final String gender;

    @Setter(AccessLevel.NONE)
    private Boolean traitor = false;

    @Setter(AccessLevel.NONE)
    private Integer denunciations = 0;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localization_id", referencedColumnName = "id")
    private LocalizationModel localization;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryModel inventory;

    private void setTraitor() {
        this.traitor = true;
    }

    public void SetDenunciations() {
        this.denunciations++;
        if (this.denunciations >= 3) {
            setTraitor();
        }
    }
}
