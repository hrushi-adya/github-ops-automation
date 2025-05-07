package com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.LinkedHashMap;
import static com.constants.Constants.*;

public class Mapper{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ClientOperations clientOperations = new ClientOperations();

    /*
    * Method to update client details.
    *
    * The commented code in the below method is implementation
    * of same logic with JsonNode and ObjectNode Class.
    * */
    public static String UpdateClientDetails(String json) {
        String updatedJson = null;
        try {

            System.out.println("File coming from GitHub: " + json);
//            JsonNode jsonNode = objectMapper.readTree(json);
//            ObjectNode objectNode = (ObjectNode) jsonNode;

            LinkedHashMap<String, LinkedHashMap<String, String>> objMapper = objectMapper.readValue(json,
                    new TypeReference<LinkedHashMap<String, LinkedHashMap<String, String>>>() {});
            LinkedHashMap<String, LinkedHashMap<String, String>> clientData = new LinkedHashMap<String, LinkedHashMap<String, String>>();
            System.out.println(objMapper);

            LinkedHashMap<String, String> obj = objMapper.get("10425");
            System.out.println(obj);

            if (System.getProperty(OPERATION).equalsIgnoreCase(ADD_CLIENT)) {
                clientData = clientOperations.addUser(objMapper);
//                objectNode = clientOperations.addClient(objectNode, objectMapper);
            } else if (System.getProperty(OPERATION).equalsIgnoreCase(UPDATE_CLIENT)) {
                clientData = clientOperations.updateClient(objMapper);
            } else if (System.getProperty(OPERATION).equalsIgnoreCase(DELETE_CLIENT)) {
                clientData = clientOperations.deleteClient(objMapper);
            }
            System.out.println("Client data after operation: " + clientData);
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            updatedJson = writer.writeValueAsString(clientData);
//            updatedJson = objectMapper.writer(new DefaultPrettyPrinter())
//                    .writeValueAsString(objectNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return updatedJson;

    }
}
