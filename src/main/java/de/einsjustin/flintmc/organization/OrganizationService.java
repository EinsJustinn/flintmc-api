package de.einsjustin.flintmc.organization;

import com.google.gson.Gson;
import de.einsjustin.flintmc.utils.FlintMCUrls;
import de.einsjustin.flintmc.http.HttpHandler;

import java.io.IOException;

public class OrganizationService {

    public Organization getOrganizationById(int id) {

        HttpHandler httpHandler = new HttpHandler();

        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_ORGANIZATION_URL, id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (string == null) throw new RuntimeException("Couldn't find organization with id " + id);

        return new Gson().fromJson(string, Organization.class);
    }
}
