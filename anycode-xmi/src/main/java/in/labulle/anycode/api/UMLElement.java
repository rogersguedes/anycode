package in.labulle.anycode.api;

import java.util.ArrayList;
import java.util.List;

public abstract class UMLElement {
    private String name;
    private UMLElement owner;
    private List<UMLStereotype> stereotypes = new ArrayList<UMLStereotype>();
    private String id;
    private boolean root;
    private boolean leaf;
    private boolean isabstract;
    private UMLVisibility visibility;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof UMLElement && getId() != null) {
            UMLElement item = (UMLElement) obj;
            return getId().equals(item.getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public String getName() {
        return name;
    }

    public UMLElement getOwner() {
        return owner;
    }

    public List<UMLStereotype> getStereotypes() {
        return stereotypes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(UMLElement newOwner) {
        if (this.owner != null && this.owner instanceof UMLPackage) {
            UMLPackage pack = (UMLPackage) this.owner;
            if (!pack.getOwnedElements().contains(this)) {
                pack.removeElement(this);
            }
        }
        this.owner = newOwner;
        if (this.owner != null && this.owner instanceof UMLPackage) {
            UMLPackage pack = (UMLPackage) this.owner;
            if (!pack.getOwnedElements().contains(this)) {
                pack.addOwnedElement(this);
            }
        }
    }

    public void setStereotypes(List<UMLStereotype> stereotypes) {
        this.stereotypes = stereotypes;
    }

    public String getFullNamespace(String separator) {
        StringBuffer w = new StringBuffer();
        UMLElement o = this.owner;
        while (o != null) {
            if ((o instanceof UMLPackage) && !(o instanceof UMLModel)) {
                if (w.length() != 0) {
                    w.insert(0, separator);
                }
                w.insert(0, o.getName());
            }
            o = o.getOwner();
        }
        return w.toString();
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isAbstract() {
        return isabstract;
    }

    public void setAbstract(boolean isabstract) {
        this.isabstract = isabstract;
    }

    public UMLModel getRoot() {
        if (this instanceof UMLModel) {
            return (UMLModel) this;
        } else if (getOwner() != null) {
            return getOwner().getRoot();
        } else {
            throw new RuntimeException(getClass().getName() + " with name " + getName() + " has no root...");
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + "|" + getName();
    }

    public UMLVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(UMLVisibility visibility) {
        this.visibility = visibility;
    }

}
