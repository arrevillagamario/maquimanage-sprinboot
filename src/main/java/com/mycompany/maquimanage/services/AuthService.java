package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.LoginDTO;

public interface AuthService {
    Usuario authenticate(LoginDTO model);
}
