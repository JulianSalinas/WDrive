package view;

public class Main {

    public static void main(String[] args) {

        long space = 4096*4; //bytes

        try{
            controller.AccountManager accountManager = new controller.AccountManager();
            accountManager.createAccount("julian", "1234", space);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }

    }
}
