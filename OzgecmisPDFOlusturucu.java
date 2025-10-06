import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OzgecmisPDFOlusturucu {

    public static void main(String[] args) {
        String name = "Ad";      // Kişinin adı soyadı bu kısma yazılacak.
        String surname = "Soyad";

        String[] workplaces = {
                "Deneyim ...",     // Daha önceki iş deneyimleri buraya girilecek.
                "Deneyim ...",
                "Deneyim ..."
        };

        String phone = "telefon no";                       // İletişim bilgileri, yetkinlikleri ve eğitim aldığı okullar bu kısma yazılacak.
        String email = "e posta adresi";
        String[] skills = {"Java", "C", "C++", "Python"};
        String[] education = {
                "Lisans Düzeyi",
                "Yüksek Lisans Düzeyi"
        };

        String photoPath = "Fotoğrafın konumu";  //fotoğrafın dosya konumu buraya girilecek
        String outputPdf = "ozgecmis.pdf";

        try {
            createResumePdf(name, surname, workplaces, phone, email, skills, education, photoPath, outputPdf);
            System.out.println("PDF oluşturuldu: " + outputPdf);
            openFileWithDefaultApp(outputPdf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createResumePdf(String name, String surname, String[] workplaces,
                                        String phone, String email, String[] skills, String[] education,
                                        String photoPath, String outputPdf)
            throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPdf));
        document.open();

        BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(bf, 12, Font.NORMAL);
        Font titleFont = new Font(bf, 14, Font.BOLD);

        // Tablo ile fotoğraf ve bilgileri yan yana yerleştirme
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 3}); // Sol: fotoğraf, Sağ: bilgiler

        // Sol: Fotoğraf
        PdfPCell photoCell;
        File photoFile = new File(photoPath);
        if (photoFile.exists()) {
            Image img = Image.getInstance(photoPath);
            img.scaleToFit(120f, 120f);
            photoCell = new PdfPCell(img, false);
        } else {
            photoCell = new PdfPCell(new Phrase("Fotoğraf yok", normalFont));
        }
        photoCell.setBorder(Rectangle.NO_BORDER);
        photoCell.setVerticalAlignment(Element.ALIGN_TOP);
        table.addCell(photoCell);

        // Sağ: Kişisel bilgiler ve iletişim
        PdfPCell infoCell = new PdfPCell();
        infoCell.setBorder(Rectangle.NO_BORDER);

        // Ad Soyad
        Paragraph namePara = new Paragraph(name + " " + surname, new Font(bf, 16, Font.BOLD));
        namePara.setSpacingAfter(5);
        infoCell.addElement(namePara);

        // İletişim bilgileri
        infoCell.addElement(new Paragraph("Telefon: " + phone, normalFont));
        infoCell.addElement(new Paragraph("E-posta: " + email, normalFont));

        table.addCell(infoCell);

        document.add(table);
        document.add(new Paragraph("\n"));

        // İş Deneyimi
        Paragraph workTitle = new Paragraph("İş Deneyimi:", titleFont);
        workTitle.setSpacingAfter(3);
        document.add(workTitle);
        for (String w : workplaces) {
            document.add(new Paragraph("• " + w, normalFont));
        }

        document.add(new Paragraph("\n"));

        // Yetenekler
        Paragraph skillTitle = new Paragraph("Yetkinlikler:", titleFont);
        skillTitle.setSpacingAfter(3);
        document.add(skillTitle);
        for (String s : skills) {
            document.add(new Paragraph("• " + s, normalFont));
        }

        document.add(new Paragraph("\n"));

        // Eğitim
        Paragraph educationTitle = new Paragraph("Eğitim:", titleFont);
        educationTitle.setSpacingAfter(3);
        document.add(educationTitle);
        for (String e : education) {
            document.add(new Paragraph("• " + e, normalFont));
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
