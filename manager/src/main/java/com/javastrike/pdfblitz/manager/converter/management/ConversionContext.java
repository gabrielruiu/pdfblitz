package com.javastrike.pdfblitz.manager.converter.management;

/**
 * Class that holds the additional data needed to make a conversion from a certain type to a Document.
 *
 * @see com.javastrike.pdfblitz.manager.converter.Converter
 *
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
//TODO: cleanup ConversionContext building, too stuffy
public interface ConversionContext {

    ConversionContext addConversionParameter(IdentifierType identifier, ConversionParameter parameter);

    ConversionContext removeConversionParameter(IdentifierType identifier);

    ConversionParameter getConversionParameter(IdentifierType identifier);


}
