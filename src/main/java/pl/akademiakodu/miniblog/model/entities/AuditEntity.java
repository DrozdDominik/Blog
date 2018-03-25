package pl.akademiakodu.miniblog.model.entities;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class AuditEntity {

    private Date added = new Date();
    private String addedBy;

    private Date modified;
    private String modifiedBy;

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
