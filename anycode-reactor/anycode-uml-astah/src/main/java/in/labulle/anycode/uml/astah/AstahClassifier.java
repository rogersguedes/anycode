package in.labulle.anycode.uml.astah;

import com.change_vision.jude.api.inf.model.IConstraint;
import in.labulle.anycode.uml.IAttribute;
import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IOperation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.IGeneralization;

public class AstahClassifier extends
		AstahElement<com.change_vision.jude.api.inf.model.IClass> implements
		IClassifier {
	private static final Logger LOG = LoggerFactory.getLogger(AstahClassifier.class);

	public AstahClassifier(com.change_vision.jude.api.inf.model.IClass astahElt) {
		super(astahElt);
	}

	public List<IAttribute> getAttributes() {
		List<IAttribute> atts = new ArrayList<IAttribute>();
		for (com.change_vision.jude.api.inf.model.IAttribute att : getAstahElement()
				.getAttributes()) {
			if (att.getAssociation() != null) {
				atts.add(new AstahRelationAttribute(att));
			} else {
				atts.add(new AstahAttribute(att));
			}
		}
		return atts;
	}

	public List<IOperation> getOperations() {
		List<IOperation> ops = new ArrayList<IOperation>();
		for(com.change_vision.jude.api.inf.model.IOperation op : getAstahElement().getOperations()) {
			ops.add(new AstahOperation(op));
		}
		return ops;
	}

	public List<? extends IClassifier> getGeneralizations() {
		List<IClassifier> gens = new ArrayList<IClassifier>();
		for(IGeneralization gen : getAstahElement().getGeneralizations()) {
			gens.add(new AstahClassifier(gen.getSuperType()));
		}
		return gens;
	}

	public List<IClassifier> getClientDependencies() {
		List<IClassifier> deps = new ArrayList<IClassifier>();
		for(IDependency dep : getAstahElement().getClientDependencies()) {
			deps.add((IClassifier)AstahElement.getElement(dep.getSupplier()));
		}
		return deps;
	}

	public boolean isPrimitive() {
		return getAstahElement().isPrimitiveType();
	}

	public boolean isLeaf() {return getAstahElement().isLeaf() ;}

	public boolean isActive() {return getAstahElement().isActive();}


	public List<String> getConstraints() {
		List<String> constraints = new ArrayList<String>(5);
		for(IConstraint c : getAstahElement().getConstraints()) {
			constraints.add(c.getName());
		}
		return constraints;
	}
}
