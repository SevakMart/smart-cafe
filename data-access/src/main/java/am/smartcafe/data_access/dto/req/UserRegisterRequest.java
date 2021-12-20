package am.smartcafe.data_access.dto.req;

import am.smartcafe.data_access.model.Role;
import lombok.Builder;

import javax.validation.constraints.*;
import java.util.Objects;
import java.util.Set;

@Builder
public class UserRegisterRequest {

    private Long id;

    @Size(min = 2, max = 50)
    @NotEmpty(message = "*Please provide your first name")
    private String firstName;

    @Size(min = 2, max = 50)
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @NotBlank
    private Set<Role> role;

    @Email
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Please enter a correct email")
    private String email;

    @NotBlank
    @NotEmpty(message = "*Please provide your password")
    private String password;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(Long id, String firstName, String lastName, Set<Role> role, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisterRequest that = (UserRegisterRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(role, that.role) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, email, password);
    }

    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
