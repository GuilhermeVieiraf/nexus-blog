package com.nexus.nexus_blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    @NotBlank(message = "O nome de utilizador é obrigatório.") // Não pode ser nulo ou vazio
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.") // Define um tamanho mínimo
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.") // Valida se a String tem formato de email
    @Column(nullable = false, unique = true)
    private String email;

}