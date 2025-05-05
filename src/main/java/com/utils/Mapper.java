// Object mapper for json file
package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static com.constants.Constants.*;

public class Mapper{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void UpdateClientDetails() {
        try {
            File filePath = new File(CLIENT_FILE_PATH);
            LinkedHashMap<String, LinkedHashMap<String, String>> objMapper = objectMapper.readValue(filePath,
                    new TypeReference<LinkedHashMap<String, LinkedHashMap<String, String>>>() {});

            System.out.println(objMapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}