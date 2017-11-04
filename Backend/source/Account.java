public class Account {

    private String username;
    private String password;
    private Drive drive;

    public Account(String username, String password, Drive drive) {
        this.username = username;
        this.password = password;
        this.drive = drive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Account)) return false;
        Account account = (Account) object;
        return username.equals(account.username);
    }

}
