package com.letscode.starwarsapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "tb_localizations")
public class LocalizationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NonNull
    @Id
    private UUID id;
    @JsonIgnore
    @OneToOne(mappedBy = "localization")
    private RebelModel rebel;
    @NonNull
    private Long latitude;
    @NonNull
    private Long longitude;
    @NonNull
    private String baseName;
}
