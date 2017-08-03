package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="SiteMap")
public class SiteMapModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NodeId")
    private long nodeId;

    @Id
    @Column(name = "NodeParentId")
    private long nodeParentId;

    @Column(name = "NodeName", unique = true)
    @NotEmpty
    private String nodeName;

    @Column(name = "NodeController", unique = true)
    private String nodeController;

    @Column(name = "IsEnabled")
    @NotEmpty
    private Boolean isEnabled;
}
