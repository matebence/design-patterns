package Pattern;

public interface LoginPresenter {
    LoginModel getModel();
    void setModel(LoginModel loginModel);
    LoginView getView();
    void setView(LoginView loginView);
    void setOnLogin(Runnable onLogin);
    void run();
    void login();
}