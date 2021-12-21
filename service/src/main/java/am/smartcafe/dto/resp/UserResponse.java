package am.smartcafe.dto.resp;

import am.smartcafe.data_access.model.Role;
import lombok.Builder;

import javax.validation.constraints.*;
import java.util.Objects;

@Builder
public class UserResponse {
  private static final String REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

  @Size(min = 2, max = 50)
  @NotEmpty(message = "*Please provide your first name")
  private String firstName;

  @Size(min = 2, max = 50)
  @NotEmpty(message = "*Please provide your last name")
  private String lastName;

  @NotBlank
  private Role role;

  @Email
  @Pattern(regexp = REGEX, message = "Please enter a correct email")
  private String email;

  public UserResponse() {}

  public UserResponse(String firstName, String lastName, Role role, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserResponse that = (UserResponse) o;
    return Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(role, that.role)
        && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, role, email);
  }

  @Override
  public String toString() {
    return "UserRegisterResponse{"
        + "firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", role="
        + role
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
