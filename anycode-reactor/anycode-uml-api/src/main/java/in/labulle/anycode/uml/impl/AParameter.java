package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IClassifier;
import in.labulle.anycode.uml.IParameter;

public class AParameter extends AElement implements IParameter {
	private IClassifier dataType;
	
	private boolean returnType = false;

	private String typeModifier;

	public IClassifier getDataType() {
		return dataType;
	}

	public void setDataType(IClassifier dataType) {
		this.dataType = dataType;
	}

	public boolean isReturnType() {
		return returnType;
	}
	
	public void setReturnType(boolean returnType) {
		this.returnType = returnType;
	}

	public String getTypeModifier() {
		return typeModifier;
	}

	public void setTypeModifier(String typeModifier) {
		this.typeModifier = typeModifier;
	}
}
