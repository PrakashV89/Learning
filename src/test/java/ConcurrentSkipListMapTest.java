import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ConcurrentSkipListMapTest {
    public static void main(String[] args) {
//        ConcurrentSkipListMap<String, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();
//        IntStream.range(0, 10).mapToObj(String::valueOf).map(Function.identity()).forEach(e -> concurrentSkipListMap.put(e, Integer.valueOf(e)));

//        concurrentSkipListMap.put(, 1);

        List.of("1", "2", "3").stream().onClose(() ->System.out.println("Stream Done!")).mapToLong(Long::valueOf).filter(e -> e < 2).forEach(System.out::println);

    }
}
