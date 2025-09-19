package br.com.UWBike.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SQ_FUNCIONARIO", sequenceName = "SQ_FUNCIONARIO", allocationSize = 1)
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO")
    @Column(name = "id_funcionario")
    private Long idFuncionario;

    @Column(name = "nome_func", length = 150, nullable = false)
    private String nomeFunc;

    @Column(name = "CPF", length = 30, nullable = false, unique = true)
    private String cpf;

    @Column(name = "salario", nullable = false)
    private double salario;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private Login login;
}