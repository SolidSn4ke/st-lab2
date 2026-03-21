package itmo.st.lab2.math;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.DoubleStream;

import itmo.st.lab2.export.Exportable;

public interface MathFunction extends Exportable {
    Double calc(Number arg);

    /**
     * @implSpec toCSV args: start, end, [step], [out_dir], [file_name]
     *           start - left bound of the interval
     *           end - right bound of the interval
     *           step - step length. optional. default is 1/100 of the interval's
     *           length
     *           out_dir - path to output directory. optional. default is current
     *           file_name - name of generated file. optional. default is
     *           'export.csv'
     */
    @Override
    default boolean toCSV(String... args) {
        try {
            Double start = Double.parseDouble(args[0]);
            Double end = Double.parseDouble(args[1]);
            Double step = args.length > 2 ? Double.parseDouble(args[2]) : (end - start) / 100;
            String outDir = args.length > 3 ? args[3] : "./";
            String fileName = args.length > 4 ? args[4] : "export.csv";

            Path file = Paths.get(outDir + fileName);
            Path dir = Paths.get(outDir);
            if (dir != null) {
                Files.createDirectories(dir);
            }

            if (file != null && !Files.exists(file)) {
                Files.createFile(file);
            }

            BufferedWriter bw = Files.newBufferedWriter(file);
            DoubleStream.iterate(start, s -> s + step).limit((long) ((end - start) / step) + 1).forEach(d -> {
                try {
                    bw.append(String.format("%.4f, %.4f\n", d, calc(d)));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
