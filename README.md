 Hamming Kod Simülatörü (Hamming Code Simulator

Bu proje, veri iletişiminde güvenliği sağlamak, hata tespiti ve hatanın hangi bitte olduğunu bularak düzeltilmesini gerçekleştirmek amacıyla **Java** programlama dili ve grafik arayüz (GUI) kullanılarak geliştirilmiş interaktif bir **Hamming Kodu Simülatörüdür**.

---
 🚀 Özellikler

  Grafiksel Kullanıcı Arayüzü (GUI):`MainFrame` ve özel çerçeve yapılarıyla desteklenen, kullanımı kolay interaktif arayüz
  Hamming Algoritması:Verileri Hamming kodlama kurallarına göre işleme ve eşlik (parity) bitlerini oluşturma
  Hata Tespiti ve Düzeltme:İletim sırasında oluşabilecek bit hatalarını tespit etme ve otomatik olarak düzeltme yeteneği
  Modüler Yapı:** `HammingCode`, `Main`, `Memory` ve arayüz bileşenleri ile düzenli sınıf mimarisi

---

 📂 Proje Yapısı

Projenin temel dizin ve dosya yapısı şu şekildedir:

hamming-kod-simülatörü/
│
├── bin/proje/              # Derlenmiş sınıf dosyaları (.class) ve kaynak kodlar
│   ├── HammingCode.java    # Hamming algoritması temel mantığı
│   ├── Main.java           # Programın başlangıç noktası
│   ├── MainFrame.java      # Arayüz ana pencere yönetimi
│   ├── AnaÇerçeve.java     # Türkçe arayüz bileşenleri
│   └── Bellek.java         # Veri saklama ve yönetim birimi
│
├── demo_video_link.pdf     # Projenin çalışmasını gösteren demo video bağlantısı
└── README.md               # Proje dokümantasyonu
