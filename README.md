# Підрахунок загальної тривалості відео

Програма, написана на Java, призначена для підрахунку загальної тривалості відеофайлів у заданій папці.

## Вимоги

Для використання цього додатка вам потрібно мати встановлене на вашому комп'ютері наступне програмне забезпечення:

- Java Development Kit (JDK)
- FFmpeg

## Використання

1. Встановіть JDK та FFmpeg на ваш комп'ютер.
2. Відкрийте проєкт у вашому редакторі коду (наприклад, IntelliJ IDEA).
3. Запустіть програму, вказавши шлях до папки, яка містить відеофайли, та шлях до виконуючого файлу FFmpeg (`ffmpeg.exe`).
4. Програма виведе загальну тривалість всіх відеофайлів у зазначеній папці.

## Приклад

```java
public class App {
    public static void main(String[] args) {
        String folderPath = "E:\\Videos\\";
        String ffmpegPath = "C:\\ffmpeg\\ffmpeg.exe";

        // Виконати підрахунок загальної тривалості відео
        calculateTotalVideoDuration(folderPath, ffmpegPath);
    }
    private static void calculateTotalVideoDuration(String folderPath, String ffmpegPath) {
        // Реалізація логіки підрахунку тривалості відео
        // Дивіться код програми для деталей
    }
}