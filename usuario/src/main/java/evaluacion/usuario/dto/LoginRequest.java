package evaluacion.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @Email(message= "Formato no valido||nombre@mail.com")
    @NotBlank(message= "El correo es obligatorio!")
    private String correo;

    @NotBlank(message= "La contraseña es obligatoria!")
    private String password;


}
