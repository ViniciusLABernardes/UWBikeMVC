package br.com.UWBike.controller;

import br.com.UWBike.dto.FuncionarioRequestDto;
import br.com.UWBike.exceptions.IdNaoEncontradoException;
import br.com.UWBike.model.Funcionario;
import br.com.UWBike.model.Patio;
import br.com.UWBike.repository.PatioRepository;
import br.com.UWBike.service.FuncionarioService;
import br.com.UWBike.service.PatioService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private PatioRepository patioRepository;
    @Autowired
    private PatioService patioService;

    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model) {
        model.addAttribute("funcionario", new FuncionarioRequestDto());
        return "formulario-funcionario";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute("funcionario") FuncionarioRequestDto dto) throws Exception {

        funcionarioService.salvarFuncionario(dto);
        return "redirect:/funcionario/funcionarios";
    }


    @PostMapping("/{id}/excluir")
    public String removerFuncionario(@PathVariable Long id, Model model) {
        try {
            funcionarioService.removerFuncionario(id);
        } catch (IdNaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
        }
        return "redirect:/funcionario/funcionarios";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioService.visualizarDadosFuncionarioEspecifico(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

            model.addAttribute("funcionario", funcionario);
            return "formulario-funcionario";


    }

    @PostMapping("/{id}/atualizar")
    public String atualizarFuncionario(@PathVariable Long id,
                                       @ModelAttribute Funcionario funcionario,
                                       Model model) {
        try {
            funcionarioService.atualizarDadosFuncionario(id, funcionario);
        } catch (IdNaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
            return "funcionario/editar";
        }

        return "redirect:/funcionario/funcionarios";
    }


    @GetMapping("/funcionarios")
    public String listarFuncionarios(Model model) {
        List<Patio> patios = funcionarioService.listarFuncionariosPorPatio();
        model.addAttribute("patios", patios);
        return "funcionarios";
    }

    @GetMapping("/realocamento")
    public String mostrarFormularioRealocamento(Model model) {
        model.addAttribute("jsonResultado", null);
        model.addAttribute("mensagem", null);
        return "realocamento";
    }


    @PostMapping("/realocamento")
    public String processarRealocamento(
            @RequestParam("idFuncionario") Long idFuncionario,
            @RequestParam("idPatio") Long idPatio,
            Model model) {

        try {

            String json = funcionarioService.conferirERealocarPatioFuncionario(idFuncionario, idPatio);

            model.addAttribute("mensagem", "Funcionário realocado com sucesso!");
            model.addAttribute("jsonResultado", json);

        } catch (IdNaoEncontradoException e) {
            model.addAttribute("mensagem", "Erro: " + e.getMessage());
            model.addAttribute("jsonResultado", null);
        } catch (Exception e) {
            model.addAttribute("mensagem", "Ocorreu um erro ao realocar o funcionário.");
            model.addAttribute("jsonResultado", null);
        }

        return "realocamento";
    }

    @GetMapping("/somatoria-salarios")
    public String calcularSomatoria(Model model) {
        try {
            String resultado = funcionarioService.calcularSomatoriaSalarios();
            model.addAttribute("mensagem", "Cálculo executado com sucesso!");
            model.addAttribute("resultado", resultado);
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao executar cálculo de salários.");
            model.addAttribute("resultado", null);
        }
        return "somatoria-salarios";
    }

}
