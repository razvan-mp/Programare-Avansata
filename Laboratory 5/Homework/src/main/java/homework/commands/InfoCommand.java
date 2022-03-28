package homework.commands;

import homework.model.base.Catalog;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import homework.utilitary.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * provides info about item from catalog, based on ID
 */
public class InfoCommand implements Command {
    public static void execute(String identifier, Catalog catalog) throws TikaException, IOException, SAXException {
        String path = Utils.getPath(identifier, catalog);
        assert path != null;
        File file = new File(path);

        //Parser method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputStream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputStream, handler, metadata, context);
        System.out.println(handler);

        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
