package com.silva.book.api;

import com.silva.book.api.AppProperties.Menu;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookController extends RouteBuilder {

    @Autowired
    private AppProperties appProperties;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").host("localhost").port(8090).bindingMode(RestBindingMode.json);

        rest().get("/hello").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Bienvenido java: " + process())).endRest();

    }

    private List<Menu> process() {
        toBase64();
        moveFile();
        return appProperties.getMenus();
    }

    public void toBase64() {
        //https://www.baeldung.com/java-base64-encode-and-decode
        //encoded
        String original = "Hello World";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes());
        System.out.println(encoded);

        //decoded
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decoded = new String(decodedBytes);

        System.out.println(decoded);

    }

    public void moveFile() {

        try {
            //https://www.baeldung.com/java-how-to-rename-or-move-a-file
            //https://www.baeldung.com/java-nio-2-file-api
            Path source = Paths.get("/Users/leo/Desktop/source/README.txt");
            Path target = Paths.get("/Users/leo/Desktop/target");
            Files.move(source, target.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            // System.out.println(source.getParent());
            /*Path source = Paths.get("");
            Files.move("", "");*/
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
