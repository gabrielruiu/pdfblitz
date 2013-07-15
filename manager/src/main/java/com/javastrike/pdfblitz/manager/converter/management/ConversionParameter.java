package com.javastrike.pdfblitz.manager.converter.management;


import com.javastrike.pdfblitz.manager.converter.management.impl.IntegerArrayConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.impl.IntegerConversionParameter;
import com.javastrike.pdfblitz.manager.converter.management.impl.StringConversionParameter;
import com.sun.xml.bind.AnyTypeAdapter;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
@XmlType
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({IntegerConversionParameter.class, IntegerArrayConversionParameter.class, StringConversionParameter.class})
public interface ConversionParameter<TYPE> {

    TYPE getValue();

    void setValue(TYPE type);
}
