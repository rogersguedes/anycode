package in.labulle.anycode.astah.plugin.generator.ui;

import in.labulle.anycode.engine.service.ICodeGenerationService;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CodeGenerationTask extends SwingWorker<Integer, String> {
	private final ICodeGenerationService codeGenerationService;
	private static final Logger LOG = LoggerFactory.getLogger(CodeGenerationTask.class);
	private final String templatePath;
	private final String outputPath;

	public CodeGenerationTask(final ICodeGenerationService service, String templatePath, String outputPath) {
		this.codeGenerationService = service;
		this.templatePath = templatePath;
		this.outputPath = outputPath;
	}

	@Override
	protected Integer doInBackground() {
		try {
			setProgress(0);
			codeGenerationService.generateCode(templatePath, outputPath);
			return 100;
		} catch (Exception e) {
			return 0;
		}
	}

}
