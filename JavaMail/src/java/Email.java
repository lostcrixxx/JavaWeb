
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    //Método que envia o email
    public static void enviaEmail(String remetente, String destinatario, String assunto, String mensagem) {

        Session session = Session.getInstance(getPropriedades(), getAuthenticator());

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("seuEmail@outlook.com")); //Seta o remetente
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)); //Define o destinatário
            message.setSubject(assunto); //Define o assunto
            message.setText(mensagem); //Mensagem do email

            Transport.send(message); //Envia o email

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //Método que retorna a autenticação de sua conta de email
    public static Authenticator getAuthenticator() {

        Authenticator autenticacao = new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {

                //Preencha com seu email e com sua senha
                return new PasswordAuthentication("seuEmail@outlook.com", "suaSenha");
            }
        };

        return autenticacao;
    }

//Método que retorna as propriedades de configuração do servidor de email
    public static Properties getPropriedades() {

        Properties props = new Properties();

        // Configuração para email gmail
        /*
        props.put("mail.smtp.host", "smtp.googlemail.com"); //SMTP do seu servidor de email
        props.put("mail.smtp.socketFactory.port", "465"); //Porta do servidor smtp
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //Define a conexão do tipo SSL
        props.put("mail.smtp.auth", "true"); //Define que é necessário autenticação
        props.put("mail.smtp.port", "465");
         */
        
        // Configuração para emails live, outlook e hotmail
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        return props;
    }
}
