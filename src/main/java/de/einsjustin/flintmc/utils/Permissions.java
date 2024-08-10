package de.einsjustin.flintmc.utils;

import lombok.Getter;

@Getter
public enum Permissions {

    EXTERNAL_SERVERS_GET(1),
    EXTERNAL_SERVERS_ACCESS(2),
    CLIPBOARD(3),
    BACKGROUND(4),
    FILE_SYSTEM(5);

    private final int value;

    Permissions(int value) {
        this.value = value;
    }
}
