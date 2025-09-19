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
import java.util.Optional;

import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PatioRepository patioRepository;

    public Funcionario salvarFuncionario(FuncionarioRequestDto.FuncionarioDTO dto) throws IdNaoEncontradoException {

        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFunc(dto.getNomeFunc());
        funcionario.setCpf(dto.getCpf());
        funcionario.setSalario(dto.getSalario());
        funcionario.setCargo(dto.getCargo());


        Login login = new Login();
        login.setFuncionario(funcionario);
        login.setLogin(dto.getLogin());
        login.setSenha(passwordEncoder.encode(dto.getSenha()));
        funcionario.setLogin(login);

        if(dto.getIdPatio() != null){
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

    public Boolean logar(LoginRequestDto login){
        try{
           Login l = funcionarioRepository.findByLogin(login.getLogin()).orElseThrow(() -> new IdNaoEncontradoException("Funcionario não encontrada"));
            if(login.equals(l.getLogin())) return true;

        } catch (IdNaoEncontradoException e) {

            throw new RuntimeException(e);

        }
        return false;
    }
    
}
