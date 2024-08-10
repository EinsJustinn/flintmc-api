package de.einsjustin.flintmc.organization;

import com.google.gson.annotations.SerializedName;
import de.einsjustin.flintmc.utils.FlintMCUrls;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Organization {
    @SerializedName("vanity_name")
    private String vanityName;
    @SerializedName("display_name")
    private String displayName;
    private boolean verified;
    @SerializedName("icon_hash")
    private String iconHash;
    @SerializedName("flint_url")
    private String flintUrl;

    public String getFlintUrl() {
        return FlintMCUrls.BASE_URL + flintUrl;
    }

    public String getIconUrl() {
        return String.format(FlintMCUrls.BRAND_ORGANIZATION_URL, iconHash);
    }
}
