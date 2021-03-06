package in.labulle.anycode.editor.ux.bean;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import in.labulle.anycode.editor.core.ApiElement;
import in.labulle.anycode.editor.core.Function;
import in.labulle.anycode.editor.core.Param;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ApiElementBean {
	private ApiElement element;

	private StringProperty name = new SimpleStringProperty(this, "name");
	private StringProperty code = new SimpleStringProperty(this, "code");
	private StringProperty description = new SimpleStringProperty(this,
			"description");

	private ListProperty<ParamBean> params = new SimpleListProperty<>(this,
			"params");

	public ApiElementBean(ApiElement e) {
		setElement(e);
	}

	public final void setElement(ApiElement model) {
		this.element = model;
		setCode(element.getCode());
		setDescription(element.getDescription());
		setName(element.getName());
		List<ParamBean> paramBeans = new ArrayList<>();
		for (Param p : model.getParams()) {
			paramBeans.add(new ParamBean(p));
		}
		params.set(new ObservableListWrapper<>(paramBeans));
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty codeProperty() {
		return code;
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public ListProperty<ParamBean> paramsProperty() {
		return params;
	}

	public String getCode() {
		return code.get();
	}

	public void setCode(String code) {
		this.code.set(code);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public void updateModel() {
		element.setCode(getCode());
		element.setDescription(getDescription());
		element.setName(getName());
		for (ParamBean b : this.params.getValue()) {
			b.updateModel();
		}
	}
	
	public boolean isFunction() {
		return element instanceof Function;
	}
}
