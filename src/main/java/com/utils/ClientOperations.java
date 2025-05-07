package com.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static com.constants.Constants.*;
import java.util.LinkedHashMap;

public class ClientOperations {

    public static LinkedHashMap<String, LinkedHashMap<String, String>> addUser(LinkedHashMap<String, LinkedHashMap<String, String>> clientData) {

        String clientId = System.getProperty(CLIENT_ID);
        String clientName = System.getProperty(CLIENT_NAME);
        String clientOrganization = System.getProperty(CLIENT_ORGANIZATION);

        LinkedHashMap<String, String> clientMap = new LinkedHashMap<String, String>();
        clientMap.put(CLIENT_NAME, clientName);
        clientMap.put(CLIENT_ORGANIZATION, clientOrganization);
        clientData.put(clientId, clientMap);

        return clientData;
    }

    // Implementation of add client using ObjectNode instead of LinkedHashMap - Same Result
    public static ObjectNode addClient(ObjectNode objectNode, ObjectMapper objectMapper) {
        String clientId = System.getProperty(CLIENT_ID);
        String clientName = System.getProperty(CLIENT_NAME);
        String clientOrganization = System.getProperty(CLIENT_ORGANIZATION);

        ObjectNode clientMap = objectMapper.createObjectNode();
        clientMap.put(CLIENT_NAME, clientName);
        clientMap.put(CLIENT_ORGANIZATION, clientOrganization);
        objectNode.set(clientId, clientMap);
        return objectNode;
    }

    public static LinkedHashMap<String, LinkedHashMap<String, String>> updateClient(LinkedHashMap<String, LinkedHashMap<String, String>> clientData) {

        try {
            String clientId = System.getProperty(CLIENT_ID);
            String clientName = System.getProperty(CLIENT_NAME);
            String clientOrganization = System.getProperty(CLIENT_ORGANIZATION);

            LinkedHashMap<String, String> clientMap = clientData.get(clientId);

            if (clientName != null) {
                clientMap.put(CLIENT_NAME, clientName);
            } else if (clientOrganization != null) {
                clientMap.put(CLIENT_ORGANIZATION, clientOrganization);
            } else {
                throw new Exception("Client name or organization is null. Please provide a valid value.");
            }

            clientData.put(clientId, clientMap);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return clientData;
    }

    public static LinkedHashMap<String, LinkedHashMap<String, String>> deleteClient(LinkedHashMap<String, LinkedHashMap<String, String>> clientData) {
        String clientId = System.getProperty(CLIENT_ID);
        if (clientData.containsKey(clientId)) {
            clientData.remove(clientId);
        }
        return clientData;
    }

}
