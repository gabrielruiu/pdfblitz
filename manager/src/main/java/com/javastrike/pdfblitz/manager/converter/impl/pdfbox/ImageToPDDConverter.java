package com.javastrike.pdfblitz.manager.converter.impl.pdfbox;

import com.javastrike.pdfblitz.manager.converter.image.ImageConverter;
import com.javastrike.pdfblitz.manager.converter.management.ConversionContext;
import com.javastrike.pdfblitz.manager.converter.management.IdentifierType;
import com.javastrike.pdfblitz.manager.exception.conversion.ConversionException;
import com.javastrike.pdfblitz.manager.model.Document;
import com.javastrike.pdfblitz.manager.model.ImageDocument;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Ruiu Gabriel Mihai (gabriel.ruiu@mail.com)
 */
public class ImageToPDDConverter implements ImageConverter<PDDocument> {

	
	private static final int IMAGE_TYPE = 1;
	private static final int RESOLUTION = 300;

    @Override
    public boolean supports(Class<Object> clazz) {
        return PDDocument.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean handlesDocumentType(Class<? extends Document> documentType) {
        return ImageDocument.class.isAssignableFrom(documentType);
    }

    // TODO: issue: the resulting pdf contains only a cropped portion of the original image
    @Override
    public PDDocument provideDocument(ImageDocument imageDocument, ConversionContext context) throws ConversionException {


        try {
            PDDocument pdDocument = new PDDocument();

            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);
            PDXObjectImage xImage = new PDJpeg(pdDocument, new ByteArrayInputStream(imageDocument.getContent()));

            PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
            contentStream.drawImage( xImage, 20, 20 );
            contentStream.close();

            return pdDocument;

        } catch (IOException e) {
            throw new ConversionException("Error converting ImageDocument to PDDocument", e);
        }
    }

    @Override
    public ImageDocument convertToDocument(PDDocument pdDocument, ConversionContext context) throws ConversionException {

        ImageDocument imageDocument;
        try {
            int pageNumber = (Integer) context.getConversionParameter(IdentifierType.IMAGE_PAGE_NUMBER).getValue();
            String imageName = (String) context.getConversionParameter(IdentifierType.DOCUMENT_NAME).getValue();

            PDPage page = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(pageNumber-1);
            BufferedImage image = page.convertToImage(IMAGE_TYPE,RESOLUTION);

            ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
            ImageIO.write(image,"jpg",contentStream);

            imageDocument = new ImageDocument(contentStream.toByteArray(), imageName);
            contentStream.close();


        } catch (IOException e) {
            throw new ConversionException("Error converting from PDDocument to Image",e);
        }
        return imageDocument;
    }
}
