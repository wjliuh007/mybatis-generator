package com.wjliuh.mbg.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class JavaClientMapperPlugin extends PluginAdapter {
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		// interfaze.addSuperInterface();
		FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType(
				getProperties().getProperty("rootInterface"));
		FullyQualifiedJavaType record = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		FullyQualifiedJavaType example = new FullyQualifiedJavaType(introspectedTable.getExampleType());
		superInterface.addTypeArgument(record);
		superInterface.addTypeArgument(example);
		interfaze.addSuperInterface(superInterface);
		interfaze.addImportedType(record);
		interfaze.addImportedType(example);
		interfaze.addImportedType(superInterface);

		return true;
	}
}
