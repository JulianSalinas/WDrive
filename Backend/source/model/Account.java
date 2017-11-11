package model;

public class Account {

    protected String username;
    protected String password;

    public String getPassword() {
        return password;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Account)) return false;
        Account account = (Account) object;
        return username.equals(account.username);
    }

}
