package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="SiteMap")
public class SiteMapModel implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NodeId")
    private long nodeId;

    @Id
    @Column(name = "NodeParentId")
    private long nodeParentId;

    @Column(name = "NodeName", unique = true)
    @NotEmpty(message = "Pole nie moze byc puste")
    private String nodeName;

    @Column(name = "NodeController", unique = true)
    private String nodeController;

    @Column(name = "IsEnabled")
    @NotNull(message = "Pole nie moze byc puste")
    private Boolean isEnabled;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getNodeParentId() {
        return nodeParentId;
    }

    public void setNodeParentId(long nodeParentId) {
        this.nodeParentId = nodeParentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeController() {
        return nodeController;
    }

    public void setNodeController(String nodeController) {
        this.nodeController = nodeController;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
