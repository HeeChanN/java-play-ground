import java.util.HashSet;
import java.util.Set;

/**
 * VM 매개변수：(JDK 8 이상) -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M
 * 실제 JDK 8 이상에서는 -Xmx6M 이옵션으로 OOM을 볼 수 있다. -> 영구세대에서 Heap으로 이동
 */

public class RuntimeConstantPoolOOM_1 {
    public static void main(String[] args) {
        // Full GC가 상수 풀을 수거하지 못하도록 집합(Set)을 이용해 상수 풀의 참조를 유지
        Set<String> set = new HashSet<String>();
        // short 타입의 범위면 6MB 크기의 영구세대에서 오버플로를 일으키기 충분함
        short i = 0;

        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}