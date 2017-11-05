package model;

public class StorageAccount extends Account {

    private Long space;

    public StorageAccount(String username, String password, Long space) {
        super(username, password);
        this.space = space;
    }

    public Long getSpace() {
        return space;
    }

    public void setSpace(Long space) {
        this.space = space;
    }

}
