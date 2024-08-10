package de.einsjustin.flintmc;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import de.einsjustin.flintmc.http.HttpHandler;
import de.einsjustin.flintmc.modifications.ModificationService;
import de.einsjustin.flintmc.organization.OrganizationService;
import de.einsjustin.flintmc.tags.Tag;
import de.einsjustin.flintmc.utils.FlintMCUrls;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlintMC {

    private final HttpHandler httpHandler;

    public FlintMC() {
        this.httpHandler = new HttpHandler();
    }

    public List<Tag> getTags() {
        String string;
        try {
            string = httpHandler.sendGetRequest(FlintMCUrls.GET_TAGS_URL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (string == null) throw new RuntimeException("Could not get tags");

        Type type = new TypeToken<Map<String, Tag>>(){}.getType();
        Map<String, Tag> stringTagMap = new Gson().fromJson(string, type);

        return new ArrayList<>(stringTagMap.values());
    }

    public ModificationService getModificationService() {
        return new ModificationService();
    }

    public OrganizationService getOrganizationService() {
        return new OrganizationService();
    }
}
