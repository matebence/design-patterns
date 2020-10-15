import javax.swing.*;
import Pattern.*;

public class App {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                LoginModel loginModel = new MyLoginModel();
                LoginPresenter loginPresenter = new MyLoginPresenter();
                loginPresenter.setModel(loginModel);
                LoginView loginView = new MyLoginView();
                loginPresenter.setView(loginView);
                loginPresenter.setOnLogin(new Runnable() {

                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null, "Welcome user!");
                    }
                });
                loginPresenter.run();
            }
        });
    }
}