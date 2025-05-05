package com.utils;

import static com.constants.Constants.*;
import java.util.LinkedHashMap;

public class ClientOperations {
    public static void addUser(LinkedHashMap<String, LinkedHashMap<String, String> clientData) {
        String clientId = System.getProperty(CLIENT_ID);
        String clientName = System.getProperty(CLIENT_NAME);
        String clientOrganization = System.getProperty(CLIENT_ORGANIZATION);

        LinkedHashMap<String, String> clientMap = new LinkedHashMap<String, String>();
        clientMap.put(CLIENT_NAME, clientName);
        clientMap.put(CLIENT_ORGANIZATION, clientOrganization);

        LinkedHashMap<String, LinkedHashMap<String, String>> parentMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        parentMap.put(clientId, clientMap);



    }

    public static void updateClient() {

    }

    public static void deleteClient() {

    }

}
