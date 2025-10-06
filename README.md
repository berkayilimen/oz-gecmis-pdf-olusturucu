# Java PDF Özgeçmiş Oluşturucu

## Genel Bakış
Bu proje, **Java** kullanılarak bir kişinin özgeçmişini PDF formatında oluşturmak için geliştirilmiştir.  
Oluşturulan PDF, fotoğraf, iletişim bilgileri, iş deneyimi, yetenekler ve eğitim bilgilerini içermektedir.  
PDF dosyası çalıştırıldığında otomatik olarak **varsayılan tarayıcıda veya varsayılan PDF görüntüleyicide** açılır.

---

## Özellikler
- Türkçe karakter desteği (İ, Ş, Ç, Ü vb.)
- Fotoğraf ekleme ve sola hizalı PDF
- Ad Soyad, Telefon ve E-posta bilgileri
- İş Deneyimi listesi 
- Yetkinlikler listesi
- Eğitim bilgileri listesi
- Profesyonel görünümlü PDF tasarımı

---

## Kullanım
1. Projeyi **IntelliJ IDEA** veya herhangi bir Java IDE ile açın.
2. `iText` kütüphanesini Maven üzerinden projenize ekleyin (`pom.xml` kullanabilirsiniz).
3. `OzgecmisPDFOlusturucu .java` dosyasındaki bilgileri kendinize göre düzenleyin:
   - `name` ve `surname` → Ad Soyad
   - `phone` ve `email` → İletişim bilgileri
   - `workplaces` → İş deneyimleri
   - `skills` → Yetkinlikler
   - `education` → Eğitim bilgileri
   - `photoPath` → Özgeçmiş sahibinin fotoğrafının dosya yolu
4. Programı çalıştırın. PDF otomatik olarak açılacaktır.

---

## Kodlama Dili
- **Java** (JDK 8 veya üstü)

---

## Gereksinimler
- [iText PDF kütüphanesi](https://mvnrepository.com/artifact/com.itextpdf/itextpdf) (v5.5.13 önerilir)

Maven kullanıyorsanız `pom.xml` içine şu satırı eklemeniz gerekir:
```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>5.5.13.3</version>
</dependency>
