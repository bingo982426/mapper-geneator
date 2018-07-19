package com.zen.tools.mybatis.plugins;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.zen.tools.mybatis.utils.RandomUtil;

/**
 * @Description : TODO
 * @Creation Date : 2017年5月5日 下午4:03:14
 * @Author : CyberZen
 * @iteration : 1.24
 */
public class MySerializablePlugin extends PluginAdapter {


  private FullyQualifiedJavaType serializable;
  private FullyQualifiedJavaType gwtSerializable;
  private boolean addGWTInterface;
  private boolean suppressJavaInterface;

  public MySerializablePlugin() {
    super();
    serializable = new FullyQualifiedJavaType("java.io.Serializable");
    gwtSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
  }

  @Override
  public boolean validate(List<String> warnings) {
    // this plugin is always valid
    return true;
  }

  @Override
  public void setProperties(Properties properties) {
    super.setProperties(properties);
    addGWTInterface = Boolean.valueOf(properties.getProperty("addGWTInterface"));
    suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface"));
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    makeSerializable(topLevelClass, introspectedTable);
    return true;
  }

  @Override
  public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    makeSerializable(topLevelClass, introspectedTable);
    return true;
  }

  @Override
  public boolean modelRecordWithBLOBsClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    makeSerializable(topLevelClass, introspectedTable);
    return true;
  }

  private void makeSerializable(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {
    if (addGWTInterface) {
      topLevelClass.addImportedType(gwtSerializable);
      topLevelClass.addSuperInterface(gwtSerializable);
    }

    if (!suppressJavaInterface) {
      topLevelClass.addImportedType(serializable);
      topLevelClass.addSuperInterface(serializable);

      Field field = new Field();
      field.setFinal(true);
      field.setInitializationString(String.valueOf(RandomUtil.nextLong()) + "L");
      field.setName("serialVersionUID");
      field.setStatic(true);
      field.setType(new FullyQualifiedJavaType("long"));
      field.setVisibility(JavaVisibility.PRIVATE);
      context.getCommentGenerator().addFieldComment(field, introspectedTable);

      topLevelClass.addField(field);
    }
  }
}
