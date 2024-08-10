package de.einsjustin.flintmc.utils;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class User {
    @SerializedName("user_name")
    private String username;
    private UUID uuid;
}
