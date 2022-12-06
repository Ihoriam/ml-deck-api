package com.petproject.portfolio.model;

import com.petproject.portfolio.user.User;
import com.petproject.portfolio.utils.UserDetailsUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false, length = 64)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name = "docker_hub_image_url")
    private String dockerHubImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "endorsement_count")
    private Integer endorsementCount = 0;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public void mapPrimitiveFields(ModelCommand command) {
        this.name = command.getName();
        this.category = command.getCategory();
        this.dockerHubImageUrl = command.getDockerHubImageUrl();
        this.description = command.getDescription();
        if (command instanceof ModelCreateCommand) {
            this.createdBy = UserDetailsUtils.getUser().orElse(null);
            this.createdAt = LocalDateTime.now();
        } else {
            this.updatedBy = UserDetailsUtils.getUser().orElse(null);
            this.updatedAt = LocalDateTime.now();
        }
    }

    // todo: remove this synchronized method. Maybe use volatile keyword or something else
    public synchronized void incrementEndorsementCount() {
        endorsementCount++;
    }
}



