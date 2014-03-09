package in.labulle.anycode.engine.groovy.core;

import static org.junit.Assert.*
import in.labulle.anycode.uml.Visibility;
import in.labulle.anycode.uml.impl.AAttribute
import in.labulle.anycode.uml.impl.AClass
import in.labulle.anycode.uml.impl.ADataType
import in.labulle.anycode.uml.impl.AStereotype

import org.junit.Before
import org.junit.Test

public class JpaDirectiveTest {
	def aClass

	@Before
	def void initClass() {
		def cl = new AClass()
		cl.setName("Person")
		def att = new AAttribute()
		att.setName("name")
		cl.addAttribute(att)
		att = new AAttribute()
		att.setName("firstname")
		cl.addAttribute(att)
		
		
		aClass = cl
	}

	@Test
	def void testAutoPrimaryKey() {
		assertTrue(JpaDirective.primaryKey(aClass).toString().indexOf("@javax.persistence.Id") != -1)
	}
	
	@Test
	def void testSinglePrimaryKey() {
		def att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		def dt = new ADataType()
		def dtcl = new AClass()
		dtcl.setName("java.lang.String")
		dt.setClassifier(dtcl)
		att.setDataType(dt)
		att.setName("code")
		aClass.addAttribute(att)
		
		assertTrue(att.hasStereotype("id"))
		
		assertTrue(JpaDirective.primaryKey(aClass).toString().indexOf("@javax.persistence.Id") != -1)
		assertTrue(JpaDirective.primaryKey(aClass).toString().indexOf("private java.lang.String code;") != -1)
		
	}
	
	@Test
	def void testCompositePrimaryKey() {
		def att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		def dt = new ADataType()
		def dtcl = new AClass()
		dtcl.setName("java.lang.String")
		dt.setClassifier(dtcl)
		att.setDataType(dt)
		att.setName("code")
		aClass.addAttribute(att)
		
		att = new AAttribute()
		att.setVisibility(Visibility.PRIVATE)
		att.addStereotype(new AStereotype("id"))
		dt = new ADataType()
		dtcl = new AClass()
		dtcl.setName("java.lang.Integer")
		dt.setClassifier(dtcl)
		att.setDataType(dt)
		att.setName("secondCode")
		aClass.addAttribute(att)
		

		
		assertTrue(JpaDirective.primaryKey(aClass).toString().indexOf("@javax.persistence.EmbeddedId") != -1)
		assertTrue(JpaDirective.primaryKey(aClass).toString().indexOf("PersonPK id") != -1)
		
	}

}
