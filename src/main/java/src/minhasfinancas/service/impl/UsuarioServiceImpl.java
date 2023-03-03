package src.minhasfinancas.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.minhasfinancas.exception.AutenticaoException;
import src.minhasfinancas.exception.RegraNegocioException;
import src.minhasfinancas.model.entity.Usuario;
import src.minhasfinancas.model.repository.UsuarioRepository;
import src.minhasfinancas.service.UsuarioService;

import static src.minhasfinancas.constant.AppConstant.MensagemErro.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario autenticar(String email, String senha) {
        var usuario = repository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new AutenticaoException(ERRO_USUARIO_NAO_ENCONTRADO);
        }

        if (!usuario.get().getSenha().equals(senha)) {
            throw new AutenticaoException(ERRO_SENHA_INVALIDA);
        }

        return usuario.get();
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        var exists = repository.existsByEmail(email);
        if (exists) {
            throw new RegraNegocioException(ERRO_EMAIL_CADASTRADO_EXISTENTE);
        }
    }
}
