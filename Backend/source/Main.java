
public class Main {

    public static void main(String[] args) {

        try{
            AccountManager accountManager = new AccountManager();
            accountManager.createAccount("julian", "1234");
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }

    }
}
