package br.com.UWBike;


import br.com.UWBike.dto.FuncionarioRequestDto;
import br.com.UWBike.dto.LoginRequestDto;
import br.com.UWBike.exceptions.IdNaoEncontradoException;
import br.com.UWBike.model.Funcionario;
import br.com.UWBike.repository.FuncionarioRepository;
import br.com.UWBike.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @Mock private DataSource dataSource;
    @Mock private Connection connection;
    @Mock private PreparedStatement ps;
    @Mock private ResultSet rs;
    @Mock private FuncionarioRepository funcionarioRepository;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks private FuncionarioService funcionarioService;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(dataSource.getConnection()).thenReturn(connection);
    }

    // -----------------------------------------------------------------------
    @Test
    void deveSalvarFuncionarioComSucesso() throws Exception {
        // Arrange
        FuncionarioRequestDto dto = new FuncionarioRequestDto();
        dto.setNomeFunc("Vinicius");
        dto.setCpf("12345678900");
        dto.setSalario(5000.0);
        dto.setCargo("Auxiliar de pátio");
        dto.setIdPatio(1L);
        dto.setLogin(new LoginRequestDto("vinicius", "senha123"));

        when(connection.prepareStatement(anyString(), any(String[].class))).thenReturn(ps);
        when(connection.prepareStatement(anyString())).thenReturn(ps);
        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getLong(1)).thenReturn(10L);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCodificada");

        // Act
        Funcionario funcionario = funcionarioService.salvarFuncionario(dto);

        // Assert
        assertNotNull(funcionario);
        assertEquals("Vinicius", funcionario.getNomeFunc());
        assertEquals(10L, funcionario.getIdFuncionario());
        verify(connection, times(1)).commit();
    }

    // -----------------------------------------------------------------------
    @Test
    void deveLancarErroAoNaoGerarIdFuncionario() throws Exception {
        // Arrange
        FuncionarioRequestDto dto = new FuncionarioRequestDto();
        dto.setNomeFunc("Rafael");
        dto.setCpf("11122233344");
        dto.setSalario(4500.0);
        dto.setCargo("Auxiliar de pátio");
        dto.setIdPatio(0);

        when(connection.prepareStatement(anyString(), any(String[].class))).thenReturn(ps);
        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(false); // não gera ID

        // Act & Assert
        assertThrows(SQLException.class, () -> funcionarioService.salvarFuncionario(dto));
        verify(connection, times(1)).rollback();
    }

    // -----------------------------------------------------------------------
    @Test
    void deveRemoverFuncionarioComSucesso() throws IdNaoEncontradoException {
        // Arrange
        Funcionario f = new Funcionario();
        f.setIdFuncionario(1L);
        f.setNomeFunc("Edvan");
        f.setCpf("99988877766");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(f));

        // Act
        funcionarioService.removerFuncionario(1L);

        // Assert
        verify(funcionarioRepository).delete(f);
    }

    // -----------------------------------------------------------------------
    @Test
    void deveLancarExcecaoAoRemoverFuncionarioInexistente() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNaoEncontradoException.class, () -> funcionarioService.removerFuncionario(1L));
    }

    // -----------------------------------------------------------------------
    @Test
    void deveAtualizarDadosFuncionario() throws IdNaoEncontradoException {
        Funcionario existente = new Funcionario();
        existente.setIdFuncionario(1L);
        existente.setNomeFunc("Rafael");
        existente.setCpf("12312312300");
        existente.setCargo("Auxiliar de pátio");
        existente.setSalario(2000.0);

        Funcionario atualizado = new Funcionario();
        atualizado.setCargo("Pleno");
        atualizado.setSalario(5000.0);
        atualizado.setCpf("12312312300");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(existente));

        funcionarioService.atualizarDadosFuncionario(1L, atualizado);

        assertEquals("Pleno", existente.getCargo());
        assertEquals(5000.0, existente.getSalario());
    }

    // -----------------------------------------------------------------------
    @Test
    void deveLancarExcecaoAoAtualizarFuncionarioInexistente() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        Funcionario f = new Funcionario();
        f.setCargo("Senior");
        f.setSalario(7000.0);

        assertThrows(IdNaoEncontradoException.class, () -> funcionarioService.atualizarDadosFuncionario(1L, f));
    }

    // -----------------------------------------------------------------------
    @Test
    void deveVisualizarFuncionarioExistente() {
        Funcionario f = new Funcionario();
        f.setIdFuncionario(5L);
        when(funcionarioRepository.findById(5L)).thenReturn(Optional.of(f));

        Optional<Funcionario> resultado = funcionarioService.visualizarDadosFuncionarioEspecifico(5L);

        assertTrue(resultado.isPresent());
        assertEquals(5L, resultado.get().getIdFuncionario());
    }
}
