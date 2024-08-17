package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClassRequest {
    @JsonProperty("class_ids")
    private int[] classIds;
}
