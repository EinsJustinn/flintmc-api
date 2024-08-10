package de.einsjustin.flintmc.modifications;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.einsjustin.flintmc.utils.FlintMCUrls;
import de.einsjustin.flintmc.http.HttpHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModificationService {

    public Modification getModificationByNamespace(String namespace) {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_MODIFICATION, namespace));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (string == null) throw new RuntimeException("Could not get modification");

        return new Gson().fromJson(string, Modification.class);
    }

    public List<String> getModificationNamespaces() {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(FlintMCUrls.GET_ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (string == null) throw new RuntimeException("Could not get modifications");

        JsonArray jsonArray = JsonParser.parseString(string).getAsJsonArray();
        List<String> modificationNamespaces = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            String namespace = jsonElement.getAsJsonObject().get("namespace").getAsString();
            modificationNamespaces.add(namespace);
        }

        return modificationNamespaces;
    }
}
