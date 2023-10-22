package com.find_city.bd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class JacksonCityNameReader {
    @JsonDeserialize(contentAs= List.class)
    ObjectMapper mapper = new ObjectMapper();



}

