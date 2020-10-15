package Pattern;

public interface LoginView {
    LoginPresenter getPresenter();
    void setPresenter(LoginPresenter loginPresenter);
    void updateModelFromView();
    void updateViewFromModel();
    void open();
    void close();
    void userRejected();
}