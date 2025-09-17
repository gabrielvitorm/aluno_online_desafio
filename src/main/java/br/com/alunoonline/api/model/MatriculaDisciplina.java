package br.com.alunoonline.api.model;

import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;
import br.com.alunoonline.api.enums.PeriodoLetivoEnum;
import br.com.alunoonline.api.enums.SituacaoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matricula_disciplina",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_aluno_disciplina_ano_periodo",
                columnNames = {"aluno_id","disciplina_id","ano_letivo","periodo_letivo"}
        ),
        indexes = {
                @Index(name = "ix_mdisc_aluno", columnList = "aluno_id"),
                @Index(name = "ix_mdisc_disciplina", columnList = "disciplina_id"),
                @Index(name = "ix_mdisc_status", columnList = "situacao_status")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_curso_id", nullable = false)
    private MatriculaCurso matriculaCurso;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @Column(name = "ano_letivo", nullable = false)
    private Integer anoLetivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodo_letivo", length = 20)
    private PeriodoLetivoEnum periodoLetivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "matricula_status", length = 20)
    private MatriculaCursoStatusEnum matriculaStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_status", length = 20)
    private SituacaoStatusEnum status;

    @Column(name = "nota1")
    private Double nota1;

    @Column(name = "nota2")
    private Double nota2;
}
