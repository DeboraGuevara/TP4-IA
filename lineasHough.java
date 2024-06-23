package TP;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class lineasHough {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        // Cargar la imagen
        Mat image = Imgcodecs.imread("C:\\Users\\Debora\\eclipse-workspace\\linea.png", Imgcodecs.IMREAD_GRAYSCALE);

        // Detectar bordes usando Canny
        Mat edges = new Mat();
        Imgproc.Canny(image, edges, 50, 200);

        // Aplicar la transformada de Hough para líneas
        Mat lines = new Mat();
        Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 50, 50, 10);

        // Convertir a color para dibujar las líneas
        Mat colorImage = new Mat();
        Imgproc.cvtColor(image, colorImage, Imgproc.COLOR_GRAY2BGR);

        // Dibujar las líneas detectadas
        for (int i = 0; i < lines.rows(); i++) {
            double[] line = lines.get(i, 0);
            Point pt1 = new Point(line[0], line[1]);
            Point pt2 = new Point(line[2], line[3]);
            Imgproc.line(colorImage, pt1, pt2, new Scalar(0, 0, 255), 2);
        }

        // Guardar la imagen resultante
        Imgcodecs.imwrite("C:\\Users\\Debora\\eclipse-workspace\\salida-linea.png", colorImage);
    }
}

