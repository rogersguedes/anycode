package in.labulle.anycode.uml.impl;

import in.labulle.anycode.uml.IRelationAttribute;

public class ARelationAttribute extends AAttribute implements IRelationAttribute {
	public enum Qualifier {
		COMPOSITION, AGGREGATION;
	}

	public enum Navigability {
		NAVIGABLE, NON_NAVIGABLE;
	}

	private ARelationAttribute otherSide;

	private Qualifier qualifier;

	private Navigability navigability;

	public boolean isRelation() {
		return true;
	}

	public ARelationAttribute getOtherSide() {
		return otherSide;
	}

	public void setOtherSide(ARelationAttribute otherSide) {
		this.otherSide = otherSide;
		if (this.otherSide.getOtherSide() != this) {
			this.otherSide.setOtherSide(this);
		}
	}

	public boolean isComposition() {
		return Qualifier.COMPOSITION.equals(this.qualifier);
	}

	public boolean isAggregation() {
		return Qualifier.AGGREGATION.equals(this.qualifier);
	}

	public boolean isNavigable() {
		return navigability == null || Navigability.NAVIGABLE.equals(this.navigability);
	}

	public void setNavigability(Navigability navigability) {
		this.navigability = navigability;
	}

	public void setQualifier(Qualifier qualifier) {
		this.qualifier = qualifier;
	}

	public boolean isManyToOne() {
		return getCardinality().isSingle() && !getOtherSide().getCardinality().isSingle();
	}

	public boolean isOneToOne() {
		return getCardinality().isSingle() && getOtherSide().getCardinality().isSingle();
	}

	public boolean isManyToMany() {
		return !getCardinality().isSingle() && !getOtherSide().getCardinality().isSingle();
	}

	public boolean isOneToMany() {
		return !getCardinality().isSingle() && getOtherSide().getCardinality().isSingle();
	}

	public boolean isOwningSide() {
		return (isManyToOne() && isBidirectionalRelation()) || (isOneToOne() && isBidirectionalRelation() && Navigability.NAVIGABLE.equals(this.navigability));
	}

	public boolean isBidirectionalRelation() {
		return isNavigable() && getOtherSide().isNavigable();
	}

}
