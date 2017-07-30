package com.mvc.model;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="SiteMap")
public class SiteMapModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SerializedName("siteId")
    public long siteId;

    @Id
    @SerializedName("parentId")
    private long parentId;

    @NotEmpty
    @Column(unique = true)
    @SerializedName("siteName")
    private String siteName;
}
