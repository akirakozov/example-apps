import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author akirakozov
 */
public class DirSizeCalculator {
    public static class DirSizeCalcTask extends ForkJoinTask<Long> {
        private final File file;
        private long result;

        public DirSizeCalcTask(File file) {
            this.file = file;
        }

        @Override
        public Long getRawResult() {
            return result;
        }

        @Override
        protected void setRawResult(Long value) {
            result = value;
        }

        @Override
        protected boolean exec() {
            System.out.println("Processing thread: " + Thread.currentThread().getName());

            long sumSize = 0;
            if (file.isFile()) {
                sumSize += file.length();
                System.out.println(file.getAbsolutePath() + " " + sumSize);
            }

            List<DirSizeCalcTask> subtasks = new ArrayList<>();
            File[] files = file.listFiles();
            if (file.isDirectory() && files != null) {
                for (File f : files) {
                    DirSizeCalcTask task = new DirSizeCalcTask(f);
                    task.fork();
                    subtasks.add(task);
                }
            }

            for (DirSizeCalcTask task : subtasks) {
                sumSize += task.join();
            }
            setRawResult(sumSize);
            return true;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        File dir = new File("/tmp");

        long size = pool.invoke(new DirSizeCalcTask(dir));
        System.out.println(dir + " size: " + size);
    }
}
