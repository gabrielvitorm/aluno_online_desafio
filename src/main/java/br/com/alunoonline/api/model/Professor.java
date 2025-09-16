package br.com.alunoonline.api.model;

import br.com.alunoonline.api.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "professor")
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "rg", nullable = false, length = 12)
    private String rg;

    @Column(name = "cpf", nullable = false, length = 15)
    private String cpf;

    @Column(name = "telefone", nullable = false, length = 12)
    private String telefone;

    @Column(name = "idade", nullable = false, length = 5)
    private Integer idade;

    @Column(name = "genero", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GeneroEnum generoEnum;

    @Column(name = "excluido", nullable = false)
    private Boolean excluido;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
