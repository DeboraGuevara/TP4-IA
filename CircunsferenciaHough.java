package TP;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class CircunsferenciaHough {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        // Cargamos la imagen que se va a analizar
        Mat image = Imgcodecs.imread("C:\\Users\\Debora\\eclipse-workspace\\motor2.png", Imgcodecs.IMREAD_GRAYSCALE);

        // Aplicar desenfoque para reducir el ruido
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(image, blurred, new Size(9, 9), 2);

        // Aplicar la transformada de Hough para circunferencias
        Mat circles = new Mat();
        Imgproc.HoughCircles(blurred, circles, Imgproc.HOUGH_GRADIENT, 1, blurred.rows() / 8, 200, 100, 0, 0);

        // Convertir a color para dibujar las circunferencias
        Mat colorImage = new Mat();
        Imgproc.cvtColor(image, colorImage, Imgproc.COLOR_GRAY2BGR);

        // Dibujar las circunferencias detectadas
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point center = new Point(circle[0], circle[1]);
            int radius = (int) circle[2];
            Imgproc.circle(colorImage, center, radius, new Scalar(0, 0, 255), 3);
            Imgproc.circle(colorImage, center, 3, new Scalar(0, 255, 0), 3);
        }

        // Guardar la imagen resultante
        Imgcodecs.imwrite("C:\\Users\\Debora\\eclipse-workspace\\salida-circulo.png", colorImage);
    }
}
