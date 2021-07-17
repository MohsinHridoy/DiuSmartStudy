package com.example.vrstyproject.model;

public class PdfModel {
    String PdfName,PdfUrl;

    public PdfModel() {
    }

    public PdfModel(String pdfName, String pdfUrl) {
        PdfName = pdfName;
        PdfUrl = pdfUrl;
    }

    public String getPdfName() {
        return PdfName;
    }

    public void setPdfName(String pdfName) {
        PdfName = pdfName;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }
}
