package com.acltabontabon.openwealth.model;

import com.acltabontabon.openwealth.enums.DocumentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Document {

    /**
     * External customer/person ID.
     */
    private String externalReference;

    /**
     * Unique document ID assigned by the custody bank.
     */
    @NonNull
    private String documentId;

    /**
     * Indicates the document category. The document category and type shall help to classify the
     * document.
     */
    private String typeCategory;

    /**
     * Indicates the specific document type e.g. Passport, ebanking contract, Form A etc.
     */
    private DocumentType type;

    /**
     * Indicates the specific document type. Attribute shall only be used if the document type is
     * not already part of the ENUM of the attribute "type" hence in exceptional cases (in case of
     * document type "other").
     */
    private String typeOverride;

    /**
     * Status of document handling - Indicates current status of a document.
     */
    private String status;

    /**
     * Unique document type ID.
     */
    private String documentTypeId;

    /**
     * Date according to ISO 8601 i.e. YYYY-MM-DD format.
     */
    private LocalDate issueDate;

    /**
     * 2-letter ISO 639-1 Language Code.
     */
    private String languageCode;

    /**
     * A media type (also known as a Multipurpose Internet Mail Extensions or MIME type) indicates
     * the nature and format of a document, file, or assortment of bytes.
     */
    @NonNull
    private String mimeType;

    /**
     * Name of the object e.g. Customer, Person, Company, Document etc.
     */
    private String fileName;

    private DigitalSignature digitalSignature;

    /**
     * Content of the document binary coded (base64-encoded file contents)
     */
    private String enclosure;
}
