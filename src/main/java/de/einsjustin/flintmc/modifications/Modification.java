package de.einsjustin.flintmc.modifications;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import de.einsjustin.flintmc.utils.FlintMCUrls;
import de.einsjustin.flintmc.http.HttpHandler;
import de.einsjustin.flintmc.organization.Organization;
import de.einsjustin.flintmc.utils.Type;
import de.einsjustin.flintmc.utils.User;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Modification {
    private int id;
    private String name;
    private String namespace;
    private boolean featured;
    private boolean verified;
    @SerializedName("organization")
    private int organizationId;
    private String author;
    private int downloads;
    @SerializedName("download_string")
    private String downloadString;
    @SerializedName("short_description")
    private String shortDescription;
    private Rating rating;
    @SerializedName("changelog")
    private String lastChangelog;
    @SerializedName("required_labymod_build")
    private int requiredLabymodBuild;
    private int releases;
    @SerializedName("last_update")
    private int lastUpdate;
    private String licence;
    @SerializedName("version_string")
    private String versionString;
    private List<String> meta;
    private List<Dependencies> dependencies;
    private List<String> permissions;
    private List<Integer> tags;
    @SerializedName("source_url")
    private String sourceUrl;
    @SerializedName("brand_images")
    private List<Brand> brands;

    public Organization getOrganization() {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_ORGANIZATION_URL, organizationId));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (string == null) throw new RuntimeException("Could not get organization");

        return new Gson().fromJson(string, Organization.class);
    }

    public String getDescription() {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_MODIFICATION_DESCRIPTION, namespace));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (string == null) throw new RuntimeException("Could not get description");

        return string;
    }

    public List<Changelog> getChangelogs() {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_MODIFICATION_CHANGELOGS, namespace));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (string == null) throw new RuntimeException("Could not get changelogs");

        JsonArray jsonArray = JsonParser.parseString(string).getAsJsonArray();
        List<Changelog> changelogs = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            Changelog changelog = new Gson().fromJson(jsonElement, Changelog.class);
            changelogs.add(changelog);
        }

        return changelogs;
    }

    public List<UserRating> getUserRatings() {

        HttpHandler httpHandler = new HttpHandler();
        String string;
        try {
            string = httpHandler.sendGetRequest(String.format(FlintMCUrls.GET_MODIFICATION_RATINGS, namespace));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (string == null) throw new RuntimeException("Could not get ratings");

        JsonArray jsonArray = JsonParser.parseString(string).getAsJsonArray();
        List<UserRating> userRatings = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            UserRating userRating = new Gson().fromJson(jsonElement, UserRating.class);
            userRatings.add(userRating);
        }

        if (userRatings.isEmpty()) throw new RuntimeException("This modification has no ratings");

        return userRatings;
    }

    @Getter
    @ToString
    public static class Rating {
        private int count;
        private double rating;
    }

    @Getter
    @ToString
    public static class Brand {
        private Type type;
        private String hash;

        public String getUrl() {
            return String.format(FlintMCUrls.BRAND_MODIFICATION_URL, hash);
        }
    }

    @Getter
    @ToString
    public static class Dependencies {
        private String namespace;
        private boolean optional;
    }

    @Getter
    @ToString
    public static class Changelog {
        private String changelog;
        @SerializedName("added_at")
        private String addedAt;
        private String release;
    }

    @Getter
    @ToString
    public static class UserRating {
        private int rating;
        private String comment;
        @SerializedName("added_at")
        private String addedAt;
        private User user;
    }
}
