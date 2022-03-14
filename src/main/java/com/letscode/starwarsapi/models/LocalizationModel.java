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
@Table(name = "tb_localizations")
public class LocalizationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @JsonIgnore
    @OneToOne(mappedBy = "localization")
    @Setter(AccessLevel.NONE)
    private RebelModel rebel;
    @NonNull
    private Long latitude;
    @NonNull
    private Long longitude;
    @NonNull
    private String baseName;
}
