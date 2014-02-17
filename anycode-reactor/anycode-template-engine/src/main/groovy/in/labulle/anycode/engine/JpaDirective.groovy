package in.labulle.anycode.engine



import groovy.text.SimpleTemplateEngine

/**
 * JPA Directive.
 * @author Jose Carreno
 *
 */
class JpaDirective {
	/**
	 * @param a attribute
	 * @return attribute full signature as a script : [visibility] [datatype] [attribute name]. e.g. : private String myVar;
	 */
	def static attribute(a) {
		def script = """\
		${a.visibility.toString().toLowerCase()} $a.dataType.name $a.name;
		"""
		return script;
	}
}