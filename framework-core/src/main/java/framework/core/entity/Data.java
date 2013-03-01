package framework.core.entity;

import java.util.List;

public class Data<T extends AbstractEntity> {

    private List<T> records;

    private Integer version;

    public List<T> getRecords() {
        return this.records;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}