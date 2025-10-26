package br.com.UWBike.repository;

import br.com.UWBike.model.Funcionario;
import br.com.UWBike.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    Optional<Funcionario> findByLogin_Login(String login);

    @Procedure(procedureName="pkg_funcionario.confere_funcionario_patio")
    String confere_funcionario_patio( @Param("p_id_func") Long idFunc,
                                      @Param("p_id_patio") Long idPatio
                                      );
    @Procedure(procedureName ="pkg_funcionario.calculo_somatoria_salario_funcionarios")
    String calcularSomatoriaSalarios();
}
