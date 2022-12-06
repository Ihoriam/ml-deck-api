package com.petproject.portfolio.model.example;

import com.petproject.portfolio.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "model_example")
public class ModelExample {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_example_seq")
    @SequenceGenerator(name = "model_example_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "prompt", nullable = false)
    private String prompt;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "response", nullable = false)
    private String response;
}