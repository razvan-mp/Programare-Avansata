package homework.commands;

import freemarker.template.*;
import homework.model.base.Catalog;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReportCommand implements Command {
    public static void execute(String path, Catalog catalog) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

        cfg.setDirectoryForTemplateLoading(new File("target\\"));

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
