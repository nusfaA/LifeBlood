package com.graise.ansafn.managelifeblood.EligibilityCriteria;

/**
 * Created by ansaf.n on 3/10/2018.
 */

public class EligibilityCriteria {

    private Id _id;
    private String criteria;
    private String value;
    private String priority;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
