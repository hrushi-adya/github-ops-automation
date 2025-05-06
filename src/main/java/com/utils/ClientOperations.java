package com.utils;

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
