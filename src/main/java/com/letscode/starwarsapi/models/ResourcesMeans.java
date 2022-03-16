package com.letscode.starwarsapi.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResourcesMeans {
    private final float weaponsMean;
    private final float ammoMean;
    private final float foodMean;
    private final float waterMean;

}
