package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import static com.constants.Constants.*;

public class Mapper{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ClientOperations clientOperations = new ClientOperations();

    public static void UpdateClientDetails() {
        try {
            File filePath = new File(CLIENT_FILE_PATH);
            LinkedHashMap<String, LinkedHashMap<String, String>> objMapper = objectMapper.readValue(filePath,
                    new TypeReference<LinkedHashMap<String, LinkedHashMap<String, String>>>() {});
            LinkedHashMap<String, LinkedHashMap<String, String>> clientData = new LinkedHashMap<String, LinkedHashMap<String, String>>();
            System.out.println(objMapper);

            LinkedHashMap<String, String> obj = objMapper.get("10425");
            System.out.println(obj);

            if (System.getProperty(OPERATION).equalsIgnoreCase(ADD_CLIENT)) {
                clientData = clientOperations.addUser(objMapper);
            } else if (System.getProperty(OPERATION).equalsIgnoreCase(UPDATE_CLIENT)) {
                clientOperations.updateClient();
            } else if (System.getProperty(OPERATION).equalsIgnoreCase(DELETE_CLIENT)) {
                clientOperations.deleteClient();
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, clientData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
