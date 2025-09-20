package br.com.UWBike.controller;

import br.com.UWBike.model.Patio;
import br.com.UWBike.service.FuncionarioService;
import br.com.UWBike.service.MotoPatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import br.com.UWBike.model.Funcionario;
import br.com.UWBike.model.MotoPatio;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/moto")
public class MotoController {

}
