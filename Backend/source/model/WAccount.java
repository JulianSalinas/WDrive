package model;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class WAccount extends Account implements ICloud {

    @XStreamOmitField
    private WFileSystem cloud;

    public WFileSystem getCloud() {
        return cloud;
    }

    public void setCloud(WFileSystem cloud) {
        this.cloud = cloud;
    }

    public WAccount(String username, String password, WFileSystem cloud) {
        super(username, password);
        this.cloud = cloud;
    }

}
