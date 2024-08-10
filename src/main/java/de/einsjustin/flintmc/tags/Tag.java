package de.einsjustin.flintmc.tags;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tag {
    private int id;
    private String tag;
    private String description;
    @SerializedName("parent_category")
    private int parentCategory;
}
