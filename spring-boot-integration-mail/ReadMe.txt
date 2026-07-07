mail trap token

a5e2563bddd9cdca22ecf736c3e332fc

play.mailer {
  host = "live.smtp.mailtrap.io"
  port = 587
  ssl = no
  tls = yes
  user = "api"
  password = "<YOUR_API_TOKEN>"
}

import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;

import java.util.List;

public class MailtrapJavaSDKTest {

    private static final String TOKEN = "<YOUR_API_TOKEN>";

    public static void main(String[] args) {
        final MailtrapConfig config = new MailtrapConfig.Builder()
            .token(TOKEN)
            .build();

        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        final MailtrapMail mail = MailtrapMail.builder()
            .from(new Address("hello@demomailtrap.co", "Mailtrap Test"))
            .to(List.of(new Address("rajula.obulreddy@gmail.com")))
            .subject("You are awesome!")
            .text("Congrats for sending test email with Mailtrap!")
            .category("Integration Test")
            .build();

        try {
            System.out.println(client.send(mail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }
    }
}


https://mailtrap.io/home

