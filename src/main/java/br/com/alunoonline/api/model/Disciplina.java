package br.com.alunoonline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "disciplina")
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "excluido")
    private Boolean excluido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", referencedColumnName = "id", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    private Curso curso;
}
