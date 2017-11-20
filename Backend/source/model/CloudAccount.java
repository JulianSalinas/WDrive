package model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class CloudAccount extends Account implements ICloud{

    @XStreamOmitField
    private CloudFileSystem cloud;

    public CloudFileSystem getCloud() {
        return cloud;
    }

    public void setCloud(CloudFileSystem cloud) {
        this.cloud = cloud;
    }

    public CloudAccount(String username, String password, CloudFileSystem cloud) {
        super(username, password);
        this.cloud = cloud;
    }

}
