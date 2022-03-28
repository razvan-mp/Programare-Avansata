package bonus.commands;

import bonus.model.base.Catalog;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * class to generate HTML report for given catalog, at given path
 */
public class ReportCommand implements Command {
    /**
     * generates HTML report with given constructor parameters
     * @param path path to generate report at
     * @param catalog catalog to generate report for
     * @throws IOException in case that path isn't found
     * @throws TemplateException in case that template isn't found
     */
    public static void execute(String path, Catalog catalog) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

        cfg.setDirectoryForTemplateLoading(new File("D:\\"));

        cfg.setIncompatibleImprovements(new Version(2, 3, 31));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<>();
        input.put("title", catalog.getName() + " report");
        input.put("catalog", catalog.getItemList());
        Template template = cfg.getTemplate("t.ftl");
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        try (Writer fileWriter = new FileWriter(path + "\\" + catalog.getName() + " report.html")) {
            template.process(input, fileWriter);
        }

    }
}
