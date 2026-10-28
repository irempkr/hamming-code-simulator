package proje;

public class HammingCode {

    // Kaç parity biti gerektiğini hesaplar
    public static int calculateParityBits(int m) {
        int r = 0;

        while (Math.pow(2, r) < (m + r + 1)) {
            r++;
        }

        return r;
    }

    // Hamming kodu oluşturur
    public static String encode(String data) {

        int m = data.length();
        int r = calculateParityBits(m);

        int n = m + r;

        char[] code = new char[n + 1];

        int dataIndex = m - 1;

        // Sağdan sola yerleştirme
        for (int pos = 1; pos <= n; pos++) {

            if (isPowerOfTwo(pos)) {

                code[pos] = '0';

            } else {

                code[pos] = data.charAt(dataIndex);
                dataIndex--;
            }
        }

        // Even parity hesapla
        for (int i = 0; i < r; i++) {

            int parityPos = 1 << i;

            int parity = 0;

            for (int pos = 1; pos <= n; pos++) {

                if ((pos & parityPos) != 0) {

                    if (code[pos] == '1') {
                        parity ^= 1;
                    }
                }
            }

            code[parityPos] = (char) ('0' + parity);
        }

        StringBuilder result = new StringBuilder();

        // Ekrana soldan yazdır
        for (int pos = n; pos >= 1; pos--) {
            result.append(code[pos]);
        }

        return result.toString();
    }

    // Hata oluşturur
    public static String createError(
            String code,
            int position) {

        if (position < 1 ||
            position > code.length()) {

            return code;
        }

        int index =
                code.length() - position;

        char[] arr =
                code.toCharArray();

        arr[index] =
                (arr[index] == '0')
                ? '1'
                : '0';

        return new String(arr);
    }

    // Sendrom hesaplar
    public static int detectError(String codeString) {

        int n = codeString.length();

        char[] code = new char[n + 1];

        for (int pos = 1; pos <= n; pos++) {

            code[pos] =
                    codeString.charAt(n - pos);
        }

        int syndrome = 0;

        int r = 0;

        while ((1 << r) < n + 1) {
            r++;
        }

        for (int i = 0; i < r; i++) {

            int parityPos = 1 << i;

            int parity = 0;

            for (int pos = 1; pos <= n; pos++) {

                if ((pos & parityPos) != 0) {

                    if (code[pos] == '1') {
                        parity ^= 1;
                    }
                }
            }

            if (parity != 0) {
                syndrome += parityPos;
            }
        }

        return syndrome;
    }

    // Hatalı biti düzeltir
    public static String correctError(
            String code,
            int position) {

        if (position <= 0 ||
            position > code.length()) {

            return code;
        }

        int index =
                code.length() - position;

        char[] arr =
                code.toCharArray();

        arr[index] =
                (arr[index] == '0')
                ? '1'
                : '0';

        return new String(arr);
    }

    // Güç kontrolü
    private static boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }

    // Girilen verinin 0 ve 1'lerden oluşup oluşmadığını kontrol eder
    public static boolean isBinary(String data) {

        for (int i = 0; i < data.length(); i++) {

            if (data.charAt(i) != '0' &&
                data.charAt(i) != '1') {
                return false;
            }
        }

        return true;
    }

    // Sadece 8,16,32 bit kabul et
    public static boolean isValidLength(String data) {

        int len = data.length();

        return len == 8 || len == 16 || len == 32;
    }
}