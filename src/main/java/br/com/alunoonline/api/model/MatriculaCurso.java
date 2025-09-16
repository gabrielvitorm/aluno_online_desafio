package br.com.alunoonline.api.model;

import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "matricula_curso",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_aluno_curso",
                columnNames = {"aluno_id", "curso_id"}
        ),
        indexes = {
                @Index(name = "ix_matricula_curso_aluno", columnList = "aluno_id"),
                @Index(name = "ix_matricula_curso_curso", columnList = "curso_id"),
                @Index(name = "ix_matricula_curso_status", columnList = "status")
        })
@Entity
public class MatriculaCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(name = "matricula_status")
    private MatriculaCursoStatusEnum status;

    @Column(name = "excluido")
    private boolean excluido;

    @CreationTimestamp
    @Column(name = "data_matricula", nullable = false)
    private LocalDateTime dataMatricula;

    @Column(name = "data_trancamento")
    private LocalDateTime dataTrancamento;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusão;
}
