package br.com.UWBike.controller;

import br.com.UWBike.dto.FuncionarioRequestDto;
import br.com.UWBike.exceptions.IdNaoEncontradoException;
import br.com.UWBike.model.Funcionario;
import br.com.UWBike.model.Patio;
import br.com.UWBike.service.FuncionarioService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("")
    public String registrarFuncionario(@ModelAttribute("item") FuncionarioRequestDto item) throws IdNaoEncontradoException {
        funcionarioService.salvarFuncionario(item);
        return "redirect:/";
    }

    @GetMapping("/remover/{id}")
    public String removerFuncionario(@PathVariable Long id) throws IdNaoEncontradoException {
        funcionarioService.removerFuncionario(id);
        return "redirect:/funcionarios";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarFuncionario(@PathVariable Long id,
                                       @ModelAttribute Funcionario funcionario) throws IdNaoEncontradoException {
        funcionarioService.atualizarDadosFuncionario(id, funcionario);
        return "redirect:/funcionarios/" + id;
    }

    @GetMapping("/{id}")
    public String visualizarFuncionario(@PathVariable Long id, Model model) throws IdNaoEncontradoException {
        Optional<Funcionario> funcionario = funcionarioService.visualizarDadosFuncionarioEspecifico(id);
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "funcionario-detalhes";
        } else {
            throw new IdNaoEncontradoException("Funcionário não encontrado");
        }
    }

    @GetMapping("/funcionarios")
    public String listarFuncionarios(Model model) {
        List<Patio> patios = funcionarioService.listarFuncionariosPorPatio();
        model.addAttribute("patios", patios);
        return "funcionarios";
    }



}
