package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@JsonSerialize
@JsonDeserialize(builder = User.Builder.class)
@Entity
@Table(name = "users")
public final class User extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String givenName;

    @NotBlank
    private String familyName;

    @Email
    private String email;

    @NotBlank
    private String description;

    private String imageURI;

    public User() {
        // No-arg constructor for hibernate
    }

    public User(final Builder builder) {
        this.nickname = builder.nickname;
        this.givenName = builder.givenName;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.description = builder.description;
        this.imageURI = builder.imageURI;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public static Builder builder() {
        return new Builder();
    }

    //Generated code begins here

    @Override
    public String toString() {
        return "User{"
            + "id=" + id
            + ", nickname='" + nickname + '\''
            + ", givenName='" + givenName + '\''
            + ", familyName='" + familyName + '\''
            + ", email='" + email + '\''
            + ", description='" + description + '\''
            + ", imageURI='" + imageURI + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
            && Objects.equals(nickname, user.nickname)
            && Objects.equals(givenName, user.givenName)
            && Objects.equals(familyName, user.familyName)
            && Objects.equals(email, user.email)
            && Objects.equals(description, user.description)
            && Objects.equals(imageURI, user.imageURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, givenName, familyName, email, description, imageURI);
    }

    // Generated code ends here

    public static final class Builder {

        private String nickname;
        private String givenName;
        private String familyName;
        private String email;
        private String description;
        private String imageURI;

        public Builder withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder withGivenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder withFamilyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImageURI(String imageURI) {
            this.imageURI = imageURI;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
