package br.com.UWBike.service;

import br.com.UWBike.dto.FuncionarioRequestDto;
import br.com.UWBike.dto.LoginRequestDto;
import br.com.UWBike.exceptions.IdNaoEncontradoException;
import br.com.UWBike.model.Funcionario;

import br.com.UWBike.model.Login;
import br.com.UWBike.model.Patio;
import br.com.UWBike.repository.FuncionarioRepository;
import br.com.UWBike.repository.PatioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PatioRepository patioRepository;

    public Funcionario salvarFuncionario(FuncionarioRequestDto dto) throws IdNaoEncontradoException {

        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFunc(dto.getNomeFunc());
        funcionario.setCpf(dto.getCpf());
        funcionario.setSalario(dto.getSalario());
        funcionario.setCargo(dto.getCargo());


        Login login = new Login();
        login.setFuncionario(funcionario);
        login.setLogin(dto.getLogin().getLogin());
        login.setSenha(passwordEncoder.encode(dto.getLogin().getSenha()));
        funcionario.setLogin(login);

        if(dto.getIdPatio() != 0){
            Patio patio = patioRepository.findById(dto.getIdPatio())
                    .orElseThrow(() -> new IdNaoEncontradoException("Pátio não encontrado"));

            ArrayList<Patio> patios = new ArrayList<>();
            patios.add(patio);
            funcionario.setPatios(patios);
        }

        return funcionarioRepository.save(funcionario);
    }

    public void removerFuncionario(Long id) throws IdNaoEncontradoException {
        Funcionario funcionarioAchado = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Funcionario não encontrada"));

        funcionarioRepository.deleteById(id);

        System.out.println("Funcionario: " + funcionarioAchado.getNomeFunc() + ", " + funcionarioAchado.getCpf() + " deletado com sucesso!");

    }

    @Transactional
    public void atualizarDadosFuncionario(Long id, Funcionario funcionario) throws IdNaoEncontradoException{
        Funcionario funcionarioAchado = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Funcionario não encontrado"));

        funcionarioAchado.setSalario(funcionario.getSalario());

        System.out.println("Funcionario: " + funcionarioAchado.getNomeFunc() + ", " + funcionario.getCpf()
                + "teve seu salário atualizado com sucesso para: " + funcionario.getSalario() );

    }

    public Optional<Funcionario> visualizarDadosFuncionarioEspecifico(Long id) {
        return funcionarioRepository.findById(id);
    }

    //função para converter os valores das senhas inseridas manualmente no banco para senhas criptografadas
    @Transactional
    public void atualizarSenhasParaBCrypt() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        for (Funcionario f : funcionarios) {
            Login login = f.getLogin();
            if (login != null) {
                String senha = login.getSenha();


                if (!senha.startsWith("$2a$") && !senha.startsWith("$2b$")) {
                    String senhaCriptografada = passwordEncoder.encode(senha);
                    login.setSenha(senhaCriptografada);
                    System.out.println("Senha do login " + login.getLogin() + " atualizada para BCrypt.");
                }
            }
        }

        funcionarioRepository.saveAll(funcionarios);
    }

    public Optional<Funcionario> buscarPorLogin(String login) {
        return funcionarioRepository.findByLogin_Login(login);
    }
    public List<Patio> listarFuncionariosPorPatio() {
        return patioRepository.findAll();
    }
    
}
