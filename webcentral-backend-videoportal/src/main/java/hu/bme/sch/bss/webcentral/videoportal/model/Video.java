package hu.bme.sch.bss.webcentral.videoportal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonSerialize
@JsonDeserialize(builder = Video.Builder.class)
@Entity
@Table(name = "videos")
public final class Video extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn
    private VideoType videoType;

    @NotBlank
    @Column(name = "long_name", nullable = false)
    private String longName;

    @NotBlank
    @Column(name = "canonical_name", nullable = false)
    private String canonicalName;

    @NotBlank
    @Column(name = "project_name", nullable = false)
    private String projectName;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Boolean visible;

    @NotNull
    @Column(nullable = false)
    private Boolean archived;

    @NotBlank
    @Column(name = "video_location", nullable = false)
    private String videoLocation;

    @NotBlank
    @Column(name = "image_location", nullable = false)
    private String imageLocation;

    public Video() {
        // No-arg constructor for hibernate
    }

    private Video(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.visible = builder.visible;
        this.videoLocation = builder.videoLocation;
        this.imageLocation = builder.imageLocation;
        this.videoType = builder.videoType;
        this.archived = false;
    }

    public Long getId() {
        return id;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(@NotNull final String longName) {
        this.longName = longName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(final String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(final Boolean visible) {
        this.visible = visible;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(final Boolean archived) {
        this.archived = archived;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(final String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(final String imageLocation) {
        this.imageLocation = imageLocation;
    }


    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "Video{"
            + "id=" + id
            + ", videoType=" + videoType
            + ", longName='" + longName + '\''
            + ", canonicalName='" + canonicalName + '\''
            + ", projectName='" + projectName + '\''
            + ", description='" + description + '\''
            + ", visible=" + visible
            + ", archived=" + archived
            + ", videoLocation='" + videoLocation + '\''
            + ", imageLocation='" + imageLocation + '\''
            + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Video video = (Video) o;
        return Objects.equals(id, video.id)
            && Objects.equals(videoType, video.videoType)
            && Objects.equals(longName, video.longName)
            && Objects.equals(canonicalName, video.canonicalName)
            && Objects.equals(projectName, video.projectName)
            && Objects.equals(description, video.description)
            && Objects.equals(visible, video.visible)
            && Objects.equals(archived, video.archived)
            && Objects.equals(videoLocation, video.videoLocation)
            && Objects.equals(imageLocation, video.imageLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, videoType, longName, canonicalName, projectName, description, visible, archived, videoLocation, imageLocation);
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private String longName;
        private String canonicalName;
        private String projectName;
        private String description;
        private Boolean visible;
        private VideoType videoType;
        private String videoLocation;
        private String imageLocation;


        public Builder withLongName(final String longName) {
            this.longName = longName;
            return this;
        }

        public Builder withCanonicalName(final String canonicalName) {
            this.canonicalName = canonicalName;
            return this;
        }

        public Builder withProjectName(final String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withVisible(final Boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder withVideoType(final VideoType videoType) {
            this.videoType = videoType;
            return this;
        }

        public Builder withVideoLocation(final String videoLocation) {
            this.videoLocation = videoLocation;
            return this;
        }

        public Builder withImageLocation(final String imageLocation) {
            this.imageLocation = imageLocation;
            return this;
        }

        public Video build() {
            return new Video(this);
        }
    }
}
