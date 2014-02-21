package in.labulle.anycode.engine.freemarker.repository.loader;

import in.labulle.anycode.engine.core.ICodeGenerationArtifact;
import in.labulle.anycode.engine.core.ITemplate;
import in.labulle.anycode.engine.exception.TemplateRuntimeException;
import in.labulle.anycode.engine.freemarker.core.CompositeTemplate;
import in.labulle.anycode.engine.freemarker.core.Template;
import in.labulle.anycode.engine.repository.AbstractDirectoryTemplateRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;

public class FilePathTemplateLoader extends FileTemplateLoader implements ITemplateLoader {

    private final File baseDir;

    public FilePathTemplateLoader(File baseDir) throws IOException {
        super(baseDir);
        this.baseDir = baseDir;
    }

    public List<ICodeGenerationArtifact> load(Configuration tConfiguration) {
        List<ICodeGenerationArtifact> templates = new ArrayList<ICodeGenerationArtifact>();
        addTemplates(baseDir, templates, tConfiguration);
        return templates;
    }

    private void addTemplates(File dir, List<ICodeGenerationArtifact> templates,
            freemarker.template.Configuration tConfig) {
        String fileNames[] = dir.list(AbstractDirectoryTemplateRepository.getTemplateFilenameFilter());
        String files[] = dir.list(AbstractDirectoryTemplateRepository.getTemplateFilter());
        try {
            for (int i = 0; i < fileNames.length; i++) {
                if (files.length > i) {
                    ITemplate tName = new Template(tConfig.getTemplate(fileNames[i]));
                    ITemplate tContent = new Template(tConfig.getTemplate(files[i]));
                    templates.add(new CompositeTemplate(tContent, tName));
                } else {
                    throw new TemplateRuntimeException("There are more filenames templates than content templates");
                }
            }
        } catch (IOException e) {
            throw new TemplateRuntimeException(e);
        }

    }



}
