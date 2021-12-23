package am.smartcafe.service.user.dto.req;

import java.util.Objects;

public class PwdChangeRequest {

  private long userId;
  private String password;
  private String newPassword;
  private String repeatNewPassword;

  public PwdChangeRequest(String password, String newPassword, String repeatNewPassword) {
    this.password = password;
    this.newPassword = newPassword;
    this.repeatNewPassword = repeatNewPassword;
  }

  public PwdChangeRequest() {}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public String getRepeatNewPassword() {
    return repeatNewPassword;
  }

  public void setRepeatNewPassword(String repeatNewPassword) {
    this.repeatNewPassword = repeatNewPassword;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PwdChangeRequest that = (PwdChangeRequest) o;
    return Objects.equals(password, that.password)
        && Objects.equals(newPassword, that.newPassword)
        && Objects.equals(repeatNewPassword, that.repeatNewPassword);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, newPassword, repeatNewPassword);
  }

  @Override
  public String toString() {
    return "UserChangePassword{"
        + "password='"
        + password
        + '\''
        + ", newPassword='"
        + newPassword
        + '\''
        + ", repeatNewPassword='"
        + repeatNewPassword
        + '\''
        + '}';
  }
}
