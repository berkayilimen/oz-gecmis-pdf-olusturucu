import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResumePdfGenerator {

    public static void main(String[] args) {
        String name = "Berkay ";
        String surname = "İlimen";

        String[] workplaces = {
                "BAYKAR – Yazılım Mühendisi (2020 – 2022)",
                "ASELSAN – Stajyer (2019)",
                "HAVELSAN – Proje Asistanı (2018 – 2019)"
        };

        String photoPath = "C:\\Users\\msı\\Downloads\\images.jpeg";
                ;
        String outputPdf = "ozgecmis.pdf";

        try {
            createResumePdf(name, surname, workplaces, photoPath, outputPdf);
            System.out.println("PDF oluşturuldu: " + outputPdf);
            openFileWithDefaultApp(outputPdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createResumePdf(String name, String surname, String[] workplaces,
                                        String photoPath, String outputPdf)
            throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPdf));
        document.open();

        // 🔤 TÜRKÇE KARAKTER DESTEKLİ FONT AYARI
        BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        Font titleFont = new Font(bf, 16, Font.BOLD);

        // Başlık
        Paragraph header = new Paragraph(name + " " + surname, titleFont);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        // Fotoğraf
        File photoFile = new File(photoPath);
        if (photoFile.exists()) {
            Image img = Image.getInstance(photoPath);
            img.scaleToFit(120f, 120f);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
        } else {
            Paragraph noPhoto = new Paragraph("(Fotoğraf bulunamadı: " + photoPath + ")", normalFont);
            noPhoto.setAlignment(Element.ALIGN_CENTER);
            document.add(noPhoto);
        }

        document.add(new Paragraph("\n", normalFont));

        // İş deneyimi
        Paragraph workTitle = new Paragraph("İş Deneyimi:", titleFont);
        document.add(workTitle);

        for (String w : workplaces) {
            Paragraph p = new Paragraph(" - " + w, normalFont);
            document.add(p);
        }

        document.close();
    }

    private static void openFileWithDefaultApp(String filepath) {
        try {
            File file = new File(filepath);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(file.toURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
