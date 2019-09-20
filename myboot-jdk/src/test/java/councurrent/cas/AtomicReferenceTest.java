package councurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/27 17:34
 * @description：
 * @modified By：
 */

public class AtomicReferenceTest {
    static AtomicReference<Integer> money = new AtomicReference<>();
    static int money2 = 19;

    public static void main(String[] args) {
        money.set(19);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.get();
                        if (m < 20) {
                            if (money.compareAndSet(m,m+20)){
                                System.out.println("余额小于20，充值成功");
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }).start();
        }
    }

}
