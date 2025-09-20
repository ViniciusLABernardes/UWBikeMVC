package br.com.UWBike.controller;

import br.com.UWBike.model.Funcionario;
import br.com.UWBike.model.MotoPatio;
import br.com.UWBike.model.Patio;
import br.com.UWBike.service.FuncionarioService;
import br.com.UWBike.service.MotoPatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("")
public class MotoPatioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private MotoPatioService motoPatioService;

    @GetMapping("/index")
    public String listarMotosFuncionarioLogado(Model model, Authentication authentication) {


        String login = authentication.getName();


        Optional<Funcionario> funcionarioOpt = funcionarioService.buscarPorLogin(login);

        List<MotoPatio> motos = new ArrayList<>();

        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();

            for (Patio patio : funcionario.getPatios()) {
                motos.addAll(motoPatioService.listarMotosPorPatio(patio));
            }
        }

        model.addAttribute("motosPatio", motos);
        return "index";
    }
}
