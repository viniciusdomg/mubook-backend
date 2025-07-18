package br.com.mubook.mubook.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Cria um construtor com os campos 'final'
public class EmailService {

    // O Spring Boot configura automaticamente este bean com as propriedades do passo 2
    private final JavaMailSender mailSender;

    /**
     * Envia um e-mail de boas-vindas com as credenciais de acesso do novo usuário.
     * @param para O e-mail do destinatário (novo usuário).
     * @param cpf O CPF do usuário, que será usado como login.
     * @param senha A senha em texto plano que o usuário cadastrou.
     */
    public void enviarEmailDeCredenciais(String para, String cpf, String senha) {
        try {
            // SimpleMailMessage é uma classe auxiliar para criar e-mails de texto simples.
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(para);
            mensagem.setSubject("Bem-vindo à Mubook! Suas Credenciais de Acesso");

            // Corpo do e-mail
            String texto = "Olá!\n\n"
                    + "Seu cadastro na plataforma Mubook foi realizado com sucesso.\n\n"
                    + "Use as seguintes credenciais para fazer login:\n"
                    + "Login: " + cpf + "\n"
                    + "Senha: " + senha + "\n\n"
                    + "Por segurança, recomendamos que você altere sua senha após o primeiro acesso.\n\n"
                    + "Atenciosamente,\n"
                    + "Equipe Mubook";

            mensagem.setText(texto);

            // Envia o e-mail
            mailSender.send(mensagem);

        } catch (Exception e) {
            // Logar o erro é uma boa prática para depuração
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            // Você pode querer lançar uma exceção customizada aqui
        }
    }

    /**
     * (Futuro) Envia um e-mail para recuperação de senha.
     * @param para O e-mail do destinatário.
     * @param token O token de reset de senha.
     */
    public void enviarEmailRecuperacaoSenha(String para, String token) {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(para);
            mensagem.setSubject("Mubook - Redefinição de Senha");

            String texto = "Olá,\n\n"
                    + "Você solicitou a redefinição da sua senha.\n"
                    + "Use o seguinte token para criar uma nova senha:\n\n"
                    + token + "\n\n"
                    + "Este token é válido por 1 hora.\n"
                    + "Se você não solicitou esta alteração, por favor, ignore este e-mail.\n\n"
                    + "Atenciosamente,\n"
                    + "Equipe Mubook";

            mensagem.setText(texto);
            mailSender.send(mensagem);

        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail de recuperação: " + e.getMessage());
        }
    }
}